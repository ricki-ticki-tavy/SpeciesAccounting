package org.ricki.catalog.openid.client;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.ricki.catalog.openid.common.IdentityMarkerAssertionSection;
import org.ricki.catalog.openid.common.PKCS7Signer;
import org.ricki.catalog.openid.common.TokenRequestAnswer;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import sun.misc.BASE64Encoder;

import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.text.SimpleDateFormat;
import java.util.Base64;
import java.util.Date;
import java.util.Objects;
import java.util.UUID;

import static org.ricki.catalog.openid.common.AuthCodeRequestStruct.*;
import static org.ricki.catalog.openid.common.MsgConstants.*;

@Named
@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class OpenIdAuthenticator {

  @Inject
  PKCS7Signer signer;

  private String keystoreFileName;

  private String keystorePassword;

  private String keyPassword;

  private String keyAlias;

  private String idpCertificateFileName;

  private String client_id;

  private String loginUrl;

  private String redirect_uri;

  private String markerUrl;

  public void configure(String client_id, String keystoreFileName, String keystorePassword
          , String keyAlias, String keyPassword, String idpCertificateFileName, String redirect_uri
          , String loginUrl, String markerUrl) {
    Objects.requireNonNull(client_id, "Не задано имя системы");
    Objects.requireNonNull(keystoreFileName, "Не задано имя файла хранилища");
    Objects.requireNonNull(keystorePassword, "Не задан пароль хранилища");
    Objects.requireNonNull(keyAlias, "Не задан псевдоним ключа");
    Objects.requireNonNull(keyPassword, "Не задан пароль ключа");
    this.keystoreFileName = keystoreFileName;
    this.keystorePassword = keystorePassword;
    this.keyPassword = keyPassword;
    this.client_id = client_id;
    this.keyAlias = keyAlias;
    this.redirect_uri = redirect_uri;
    this.loginUrl = loginUrl;
    this.markerUrl = markerUrl;
    this.idpCertificateFileName = idpCertificateFileName;
    signer.evictKeyStoreHolder();

  }

  private String sign(String data) {
    // подпишем
    try {
      return new BASE64Encoder().encode(signer.sign(keystoreFileName, keystorePassword, keyAlias, keyPassword, data));
    } catch (Throwable th) {
      // TODO exception handler
      return null;
    }

  }

  public String createAuthRequest(String state, String redirectUrlPrefix) {
    String access_type = "online";
    SimpleDateFormat fmt = new SimpleDateFormat("yyyy.MM.dd HH:mm:ss +0000");
    String timestamp = fmt.format(new Date());

    String scope = DEFAULT_AUTH_SCOPE;
    // подпишем
    String client_secret = sign(scope + timestamp + client_id + state);

    StringBuilder sb = new StringBuilder(2048);
    try {
      sb.append(RESPONSE_TYPE_PARAM_NAME + "=" + OPENID_AUTH_CODE_VALUE)
              .append("&" + CLIENT_ID_PARAM_NAME + "=").append(URLEncoder.encode(client_id, "UTF-8"))
              .append("&" + REDIRECT_URI_PARAM_NAME + "=").append(URLEncoder.encode(redirectUrlPrefix + redirect_uri, "UTF-8"))
//              .append("&" + REDIRECT_URI_PARAM_NAME + "=").append(URLEncoder.encode(back_uri, "UTF-8"))
              .append("&" + SCOPE_PARAM_NAME + "=").append(URLEncoder.encode(scope, "UTF-8"))
              .append("&" + CLIENT_SECRET_PARAM_NAME + "=").append(URLEncoder.encode(client_secret, "UTF-8"))
              .append("&" + STATE_PARAM_NAME + "=").append(URLEncoder.encode(state, "UTF-8"))
              .append("&" + TIMESTAMP_PARAM_NAME + "=").append(URLEncoder.encode(timestamp, "UTF-8"))
              .append("&" + ACCESS_TYPE_PARAM_NAME + "=").append(URLEncoder.encode(access_type, "UTF-8"));
      return sb.toString();
    } catch (UnsupportedEncodingException uee) {
      return null;
    }
  }

  public String createAuthRequest(String state) {
    return createAuthRequest(state, "");
  }

  public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
          throws IOException, ServletException {
    HttpSession session = ((HttpServletRequest) request).getSession(true);

    String code = request.getParameter(CODE_PARAM_NAME);

    if (code != null) {
      // пришел авторизационный код
      requestToken(session, request);
      session.setAttribute("SPRING_SECURITY_CONTEXT", new Date());
      ((HttpServletResponse) response).sendRedirect(redirect_uri);
    } else {
      String state = UUID.randomUUID().toString();
      String redirectParams = createAuthRequest(state);
      if (redirectParams != null) {
        session.setAttribute(OPENID_AUTH_CODE_REQUEST_DATETIEM_PARAMNAME, new Date());
        session.setAttribute(OPENID_STATE_PARAMNAME, state);
        ((HttpServletResponse) response).sendRedirect(loginUrl + "?" + redirectParams);
      }
    }
  }

  private String sendPostRequest(String address, String params) throws IOException {
    URL url = new URL(address);
    byte[] postDataBytes = params.toString().getBytes("UTF-8");
    StringBuilder sb;

    HttpURLConnection conn = (HttpURLConnection) url.openConnection();
    try {
      conn.setRequestMethod("POST");
      conn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
      conn.setRequestProperty("Content-Length", String.valueOf(postDataBytes.length));
      conn.setDoOutput(true);
      conn.getOutputStream().write(postDataBytes);

      Reader in = new BufferedReader(new InputStreamReader(conn.getInputStream(), "UTF-8"));

      sb = new StringBuilder(3000);
      for (int c; (c = in.read()) >= 0; )
        sb.append((char) c);
    } finally {
      conn.disconnect();
    }
    return sb.toString();
  }
  //-----------------------------------------------------------------------------------------

  /**
   * Формирует и отправляет запрос на обмен кода авторизации на маркер доступа.
   *
   * @param session
   * @param request
   */
  private void requestToken(HttpSession session, ServletRequest request) {
    // пришел авторизационный код. Сверим статус с тем, который мы отправляли
    Date authReqDate = (Date) session.getAttribute(OPENID_AUTH_CODE_REQUEST_DATETIEM_PARAMNAME);
    if ((new Date().getTime() - authReqDate.getTime()) > 30000) {
      throw new RuntimeException("таймаут ответа.");
    }
    String receivedStatus = request.getParameter(STATE_PARAM_NAME);
    String lastRequestStatus = (String) session.getAttribute(OPENID_STATE_PARAMNAME);
    if (!lastRequestStatus.equals(receivedStatus)) {
      throw new RuntimeException("Код запроса не совпал.");
    }
    // все совпало. формируем запрос обмена авторизационного кода на токен
    String code = request.getParameter(CODE_PARAM_NAME);
    String state = UUID.randomUUID().toString();
    String tokenParams = buildTokenRequestParams(code, state);

//    пошлем запрос на сервер
    try {
      String requestResult = sendPostRequest(markerUrl, tokenParams);
      Gson json = new GsonBuilder().create();
      TokenRequestAnswer answer = json.fromJson(requestResult, TokenRequestAnswer.class);
      if (answer.state.equals(state)) {
        session.setAttribute(OPENID_CURRENT_TOKEN_PARAMNAME, answer.access_token);
//        session.setAttribute(OPENID_REFRESH_TOKEN_PARAMNAME, answer.refresh_token);
        session.setAttribute(OPENID_TOKEN_EXPIRED_PARAMNAME, new Date(new Date().getTime() + answer.expires_in * 1000));
        session.removeAttribute(OPENID_AUTH_CODE_PARAMNAME);
        session.setAttribute(OPENID_AUTH_CODE_PARAMNAME, code);
        String[] parts = answer.id_token.split("\\.");

        IdentityMarkerAssertionSection identityMarker = json.fromJson(new String(Base64.getUrlDecoder()
                .decode(parts[1].getBytes(StandardCharsets.UTF_8)), StandardCharsets.UTF_8), IdentityMarkerAssertionSection.class);
        session.setAttribute(OPENID_SUB_CODE_PARAMNAME, identityMarker);

      }
    } catch (
            Throwable th)

    {
      throw new RuntimeException(th);
    }

  }
  //-----------------------------------------------------------------------------------------

  /**
   * Формирует запрос обмена кода авторизации на токен
   *
   * @param code
   * @return
   */
  private String buildTokenRequestParams(String code, String state) {
    StringBuilder sb = new StringBuilder(2000);
    String scope = DEFAULT_AUTH_SCOPE;
    SimpleDateFormat fmt = new SimpleDateFormat("yyyy.MM.dd HH:mm:ss +0000");
    String timestamp = fmt.format(new Date());
    // подпишем
    String client_secret = sign(scope + timestamp + client_id + state);

    try {
      sb.append(CLIENT_ID_PARAM_NAME + "=").append(URLEncoder.encode(client_id, "UTF-8"))
              .append("&" + CODE_PARAM_NAME + "=").append(URLEncoder.encode(code, "UTF-8"))
              .append("&" + GRANT_TYPE_PARAM_NAME).append("=").append(OPENID_TOKEN_GRANT_TYPE_VALUE)
              .append("&" + CLIENT_SECRET_PARAM_NAME + "=").append(URLEncoder.encode(client_secret, "UTF-8"))
              .append("&" + STATE_PARAM_NAME + "=").append(URLEncoder.encode(state, "UTF-8"))
              .append("&" + REDIRECT_URI_PARAM_NAME + "=").append(URLEncoder.encode(redirect_uri, "UTF-8"))
              .append("&" + SCOPE_PARAM_NAME + "=").append(URLEncoder.encode(scope, "UTF-8"))
              .append("&" + TIMESTAMP_PARAM_NAME + "=").append(URLEncoder.encode(timestamp, "UTF-8"))
              .append("&" + TOKEN_TYPE_PARAM_NAME + "=").append(URLEncoder.encode(DEFAULT_AUTH_TOKEN_TYPE, "UTF-8"));
    } catch (Throwable th) {
      return null;
    }
    return sb.toString();
  }
}
