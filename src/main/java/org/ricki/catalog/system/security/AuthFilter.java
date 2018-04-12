package org.ricki.catalog.system.security;


import org.apache.catalina.connector.RequestFacade;
import org.ricki.catalog.openid.client.OpenIdAuthenticator;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.context.support.SpringBeanAutowiringSupport;

import javax.inject.Inject;
import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;

import static org.ricki.catalog.openid.common.MsgConstants.OPENID_SUB_CODE_PARAMNAME;


public class AuthFilter implements Filter {
  @Inject
  OpenIdAuthenticator openIdAuthenticator;

  @Value("${openid.keystore.filename:/home/dsporykhin/openid-esia.jks}")
  private String keystoreFileName;

  @Value("${openid.keystore.password:openid-pass}")
  private String keystorePassword;

  @Value("${openid.keystore.key.password:openid-pass}")
  private String keyPassword;

  @Value("${openid.keystore.key.alias:openid-esia}")
  private String keyAlias;

  @Value("${openid.keystore.client_id:sso}")
  private String client_id;

  @Value("${openid.login.url:http://localhost:8080/login}")
  private String openidLoginUrl;

  @Value("${openid.login.marker.url:http://localhost:8080/token/factory}")
  private String openidMarkerUrl;

  /**
   * либо этот параметр, либо idpKeystoreFileName, idpKeystorePassword, idpKeyAlias, idpKeyPassword
   */
  @Value("${openid.idp.certificate.filename:}")
  private String idpCertificateFileName;

  @Value("${openid.idp.keystore.filename:}")
  private String idpKeystoreFileName;

  @Value("${openid.idp.keystore.password:openid-pass}")
  private String idpKeystorePassword;

  @Value("${openid.idp.key.alias:openid-esia}")
  private String idpKeyAlias;

  @Value("${openid.idp.key.password:openid-pass}")
  private String idpKeyPassword;

  public String thisAppAddress;

  @Override
  public void init(FilterConfig filterConfig) throws ServletException {
    if (openIdAuthenticator == null) {
      SpringBeanAutowiringSupport.processInjectionBasedOnCurrentContext(this);
    }
    thisAppAddress = "http://localhost:8080";
    openIdAuthenticator.configure(client_id, keystoreFileName, keystorePassword, keyAlias, keyPassword
            , idpCertificateFileName, thisAppAddress, openidLoginUrl, openidMarkerUrl);
  }
  //----------------------------------------------------------------------------------------------

  public void configure(String client_id, String keystoreFileName, String keystorePassword
          , String keyAlias, String keyPassword, String idpCertificateFileName, String redirect_uri
          , String loginUrl, String markerUrl) {

    this.keystoreFileName = keystoreFileName;
    this.keystorePassword = keystorePassword;
    this.keyPassword = keyPassword;
    this.client_id = client_id;
    this.keyAlias = keyAlias;
    this.thisAppAddress = redirect_uri;
    this.openidLoginUrl = loginUrl;
    this.openidMarkerUrl = markerUrl;
    this.idpCertificateFileName = idpCertificateFileName;

    openIdAuthenticator.configure(client_id, keystoreFileName, keystorePassword, keyAlias, keyPassword
            , idpCertificateFileName, thisAppAddress, openidLoginUrl, openidMarkerUrl);
  }
  //----------------------------------------------------------------------------------------------

  /**
   * Для вызова из другого фильтра. Не выполняется никаких проверок в отношении путей, на которые не нужно распространять действие фильтра
   *
   * @param request
   * @param response
   * @param chain
   * @throws IOException
   * @throws ServletException
   */
  public void authenticatorDoFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
    openIdAuthenticator.doFilter(request, response, chain);
  }
  //----------------------------------------------------------------------------------------------

  @Override
  public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {

    String path = ((RequestFacade) request).getPathInfo();
    HttpSession session = ((HttpServletRequest) request).getSession(true);

    if (!"/login".equals(path) && !path.contains("/VAADIN/") && !path.contains("/VAADIN/") && !path.contains("/UIDL/") && session.getAttribute(OPENID_SUB_CODE_PARAMNAME) == null) {
      authenticatorDoFilter(request, response, chain);
    } else {
      chain.doFilter(request, response);
    }
  }
}
