package org.ricki.catalog.system.openid.client;

import org.ricki.catalog.system.openid.common.PKCS7Signer;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import sun.misc.BASE64Encoder;

import javax.inject.Inject;
import javax.inject.Named;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

import static org.ricki.catalog.system.openid.server.AuthCodeRequestStruct.*;

@Named
@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class OpenIdAuthenticator {

  @Inject
  PKCS7Signer signer;

  private String keystoreFileName;

  private String keystorePassword;

  private String keyPassword;

  private String keyAlias;

  private String client_id;

  String redirect_uri;

  public String createAuthRequest() {
    String access_type = "online";
    SimpleDateFormat fmt = new SimpleDateFormat("yyyy.MM.dd HH:mm:ss +0000");
    String timestamp = fmt.format(new Date());

    String state = UUID.randomUUID().toString();

    String scope = "fullname";
    // подпишем
    String signingData = scope + timestamp + client_id + state;
    String client_secret = "";
    try {
      client_secret = new BASE64Encoder().encode(signer.sign(keystoreFileName, keystorePassword, keyAlias, keyPassword, signingData));
    } catch (Throwable th) {
      // TODO exception handler
      return null;
    }

    StringBuilder sb = new StringBuilder(2048);
    try {
      sb.append("response_type=code")
              .append("&" + CLIENT_ID_PARAM_NAME + "=").append(URLEncoder.encode(client_id, "UTF-8"))
              .append("&" + REDIRECT_URI_PARAM_NAME + "=").append(URLEncoder.encode(redirect_uri, "UTF-8"))
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


  public void setKeystoreFileName(String fileName) {
    keystoreFileName = fileName;
  }

  public void setKeystorePassword(String password) {
    keystorePassword = password;
  }

  public void setKeyPassword(String keyPassword) {
    this.keyPassword = keyPassword;
  }

  public void setKeyAlias(String keyAlias) {
    this.keyAlias = keyAlias;
  }

  public void setClient_id(String client_id) {
    this.client_id = client_id;
  }

  public void setRedirect_uri(String redirect_uri) {
    this.redirect_uri = redirect_uri;
  }
}
