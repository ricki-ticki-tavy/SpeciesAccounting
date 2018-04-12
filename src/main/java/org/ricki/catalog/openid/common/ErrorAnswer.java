package org.ricki.catalog.openid.common;

/**
 * Структура для возврата данных об ошибке
 */
public class ErrorAnswer {

  public static final String ERROR_UNSUPPORTED_RESPONSE_TYPE = "unsupported_response_type";
  public static final String ERROR_INVALID_GRANT = "invalid_grant";

  public String error;

  public ErrorAnswer(String error) {
    this.error = error;
  }
}
