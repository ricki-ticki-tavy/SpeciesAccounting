package org.ricki.catalog.system.openid;

import org.ricki.catalog.entity.UserAccount;

import java.util.Date;

public class UserTokenInfo {
  public String accessCode;
  public UserAccount userAccount;
  public String token;
  public Date validUntil;
  public String refreshToken;
  public String I_S_code;

  private void init(String I_S_code, String accessCode, String token, String refreshToken, Date validUntil, UserAccount userAccount) {
    this.token = token;
    this.refreshToken = refreshToken;
    this.userAccount = userAccount;
    this.validUntil = validUntil;
    this.I_S_code = I_S_code;
    this.accessCode = accessCode;
  }

  public UserTokenInfo(String I_S_code, String accessCode, String token, String refreshToken, Date validUntil, UserAccount userAccount) {
    init(I_S_code, accessCode, token, refreshToken, validUntil, userAccount);
  }
}
