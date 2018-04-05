package org.ricki.catalog.web.filter.security;


import org.ricki.catalog.system.openid.client.OpenIdAuthenticator;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.context.support.SpringBeanAutowiringSupport;

import javax.inject.Inject;
import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;


public class AuthFilter implements Filter {
  @Inject
  OpenIdAuthenticator openIdAuthenticator;

  @Value("${keystore.filename:.}")
  private String keystoreFileName;

  @Value("${keystore.password:openid-pass}")
  private String keystorePassword;

  @Value("${keystore.key.password:openid-pass}")
  private String keyPassword;

  @Value("${keystore.key.alias:openid-esia}")
  private String keyAlias;

  @Value("${keystore.client_id:sso}")
  private String client_id;

  @Override
  public void init(FilterConfig filterConfig) throws ServletException {
    if (openIdAuthenticator == null) {
      SpringBeanAutowiringSupport.processInjectionBasedOnCurrentContext(this);
    }
    openIdAuthenticator.setKeystoreFileName(keystoreFileName);
    openIdAuthenticator.setKeystorePassword(keystorePassword);
    openIdAuthenticator.setKeyPassword(keyPassword);
    openIdAuthenticator.setClient_id(client_id);
    openIdAuthenticator.setKeyAlias(keyAlias);
  }

  @Override
  public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
    String path = ((HttpServletRequest) request).getServletPath();
    HttpSession session = ((HttpServletRequest) request).getSession(true);

    if (session.getAttribute("SPRING_SECURITY_CONTEXT") == null) {
      String redirectParams = openIdAuthenticator.createAuthRequest();
    }
    chain.doFilter(request, response);
  }
}
