package org.ricki.catalog.system.openid.server;

import org.ricki.catalog.entity.UserAccount;

import java.util.Date;

public class UserTokenInfo {
  public String accessCode;
  public UserAccount userAccount;
  public String token;
  public Date validUntil;
  public String refreshToken;
  public String I_S_code;
  public Date loginDatetime;
  public Date tokenValidFrom;
  public int expires_in;

  private void init(String I_S_code, String accessCode, String token, String refreshToken, int expires_in, UserAccount userAccount, Date loginDatetime) {
    this.token = token;
    this.refreshToken = refreshToken;
    this.userAccount = userAccount;
    setExpireDate(expires_in);
    this.I_S_code = I_S_code;
    this.accessCode = accessCode;
    this.tokenValidFrom = new Date();
    this.loginDatetime = loginDatetime;
  }

  public void setExpireDate(int expires_in) {
    this.expires_in = expires_in;
    this.validUntil = new Date(new Date().getTime() + expires_in * 1000);
  }

  public UserTokenInfo(String I_S_code, String accessCode, String token, String refreshToken, int expires_in, UserAccount userAccount, Date loginDatetime) {
    init(I_S_code, accessCode, token, refreshToken, expires_in, userAccount, loginDatetime);
  }
}
