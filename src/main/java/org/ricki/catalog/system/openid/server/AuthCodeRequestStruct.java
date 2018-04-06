package org.ricki.catalog.system.openid.server;

import javax.servlet.http.HttpServletRequest;

public class AuthCodeRequestStruct {

  public static final String CLIENT_ID_PARAM_NAME = "client_id";
  public static final String RESPONSE_TYPE_PARAM_NAME = "response_type";
  public static final String REDIRECT_URI_PARAM_NAME = "redirect_uri";
  public static final String SCOPE_PARAM_NAME = "scope";
  public static final String CLIENT_SECRET_PARAM_NAME = "client_secret";
  public static final String STATE_PARAM_NAME = "state";
  public static final String TIMESTAMP_PARAM_NAME = "timestamp";
  public static final String ACCESS_TYPE_PARAM_NAME = "access_type";

  public String client_id;
  public String response_type;
  public String redirect_uri;
  public String scope;
  public String client_secret;
  public String state;
  public String timestamp;
  public String access_type;

  public static AuthCodeRequestStruct fromRequest(HttpServletRequest request) {
    AuthCodeRequestStruct authStruct = new AuthCodeRequestStruct();
    authStruct.client_id = request.getParameter(RESPONSE_TYPE_PARAM_NAME);
    authStruct.response_type = request.getParameter(CLIENT_ID_PARAM_NAME);
    authStruct.redirect_uri = request.getParameter(REDIRECT_URI_PARAM_NAME);
    authStruct.scope = request.getParameter(SCOPE_PARAM_NAME);
    authStruct.client_secret = request.getParameter(CLIENT_SECRET_PARAM_NAME);
    authStruct.state = request.getParameter(STATE_PARAM_NAME);
    authStruct.timestamp = request.getParameter(TIMESTAMP_PARAM_NAME);
    authStruct.access_type = request.getParameter(ACCESS_TYPE_PARAM_NAME);

    return authStruct;
  }
}
