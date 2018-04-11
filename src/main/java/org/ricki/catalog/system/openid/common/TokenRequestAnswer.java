package org.ricki.catalog.system.openid.common;

/**
 * Структура для упаковки / распаковки ответа по запросу обмена кода авторизации на маркер доступа
 */
public class TokenRequestAnswer {
  public String access_token;
  public int expires_in;
  public String state;
  public String token_type;
  public String refresh_token;
}
