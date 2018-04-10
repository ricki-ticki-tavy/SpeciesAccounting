package org.ricki.catalog.web.filter.security;


import org.apache.catalina.connector.RequestFacade;
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

  @Value("${openid.login.marker.url:http://localhost:8080/login/marker}")
  private String openidMarkerUrl;

  public String thisAppAddress;

  @Override
  public void init(FilterConfig filterConfig) throws ServletException {
    if (openIdAuthenticator == null) {
      SpringBeanAutowiringSupport.processInjectionBasedOnCurrentContext(this);
    }
    thisAppAddress = "http://localhost:8080";
    openIdAuthenticator.configure(client_id, keystoreFileName, keystorePassword, keyAlias, keyPassword
            , thisAppAddress, openidLoginUrl, openidMarkerUrl);
  }

  @Override
  public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {

    String path = ((RequestFacade) request).getPathInfo();
    HttpSession session = ((HttpServletRequest) request).getSession(true);

    if (!"/login".equals(path) && !path.contains("/VAADIN/") && !path.contains("/UIDL/") && session.getAttribute("SPRING_SECURITY_CONTEXT") == null) {
      openIdAuthenticator.doFilter(request, response, chain);
//      if (request.getParameter("code") != null){
//          // пришел авторизационный код
//        openIdAuthenticator.doFilter(request, response, chain);
//        session.setAttribute("SPRING_SECURITY_CONTEXT", new Date());
//        chain.doFilter(request, response);
//      } else {
//        String redirectParams = openIdAuthenticator.createAuthRequest();
//        ((HttpServletResponse) response).sendRedirect(openidLoginUrl + "?" + redirectParams);
//      }
    } else {
      chain.doFilter(request, response);
    }
  }
}
