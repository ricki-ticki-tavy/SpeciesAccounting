package org.ricki.catalog.system.openid;

import org.ricki.catalog.entity.UserAccount;

import java.util.Date;

public class UserTokenInfo {
  public UserAccount userAccount;
  public String token;
  public Date validUntil;
  public String refreshToken;
  public String I_S_code;

  private void init(String I_S_code, String token, String refreshToken, Date validUntil, UserAccount userAccount) {
    this.token = token;
    this.refreshToken = refreshToken;
    this.userAccount = userAccount;
    this.validUntil = validUntil;
    this.I_S_code = I_S_code;
  }

  public UserTokenInfo(String I_S_code, String token, String refreshToken, Date validUntil, UserAccount userAccount) {
    init(I_S_code, token, refreshToken, validUntil, userAccount);
  }
}
