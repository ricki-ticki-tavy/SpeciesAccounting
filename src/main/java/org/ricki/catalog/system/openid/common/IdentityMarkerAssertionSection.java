package org.ricki.catalog.system.openid.common;

import java.util.Date;

/**
 * Секция набора утверждений идентификатора маркера
 */
public class IdentityMarkerAssertionSection {
  /**
   * организация, выпустившая маркер
   */
  public String iss;

  /**
   * идентификатор субъекта, в качестве значения которого указывается oid. Этот идентификатор уникален для каждого субъекта
   */
  public String sub;

  /**
   * время прекращения действия, указывается в секундах с 1 января 1970 г. 00:00:00 GMT
   */
  public long exp;

  /**
   * время аутентификации – время, когда произошла аутентификация
   * пользователя, указывается в секундах с 1 января 1970 г. 00:00:00 GMT
   */
  public long auth_time;

  /**
   * время начала действия – в секундах с 1 января 1970 г. 00:00:00 GMT, т.е. маркер
   * нельзя обрабатывать до наступления указанного времени
   */
  public long nbf;

  public IdentityMarkerAssertionSection(String iss, String sub, Date expDate, Date loginTime, Date startActDate) {
    this.iss = iss;
    this.auth_time = loginTime.getTime() / 1000;
    this.exp = expDate.getTime() / 1000;
    this.nbf = startActDate.getTime() / 1000;
    this.sub = sub;
  }
}
