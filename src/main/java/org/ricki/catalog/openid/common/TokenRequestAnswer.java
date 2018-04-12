package org.ricki.catalog.openid.common;

/**
 * Структура для упаковки / распаковки ответа по запросу обмена кода авторизации на маркер доступа
 */
public class TokenRequestAnswer {
  public String id_token;
  public String access_token;
  public int expires_in;
  public String state;
  public String token_type;

  public TokenRequestAnswer(String id_token, String access_token, int expires_in, String state, String token_type) {
    this.access_token = access_token;
    this.expires_in = expires_in;
    this.state = state;
    this.token_type = token_type;
    this.id_token = id_token;
  }
}
