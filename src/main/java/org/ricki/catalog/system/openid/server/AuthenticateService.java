package org.ricki.catalog.system.openid.server;

import org.ricki.catalog.entity.UserAccount;
import org.ricki.catalog.service.UserAccountService;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;

import javax.inject.Inject;
import javax.inject.Named;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import static org.ricki.catalog.system.openid.common.ErrorAnswer.ERROR_INVALID_GRANT;

@Named
@Scope(ConfigurableBeanFactory.SCOPE_SINGLETON)
public class AuthenticateService {

  @Inject
  UserAccountService userAccountService;

  private Map<String, UserTokenInfo> tokenToTokenInfo = new HashMap<>(100);
  private Map<String, UserTokenInfo> refreshTokenToTokenInfo = new HashMap<>(100);
  private Map<String, UserTokenInfo> accessCodeToTokenInfo = new HashMap<>(100);

  private String generateRandomString(int len) {
    char[] table = new char[]{'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O',
            'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z', '0', '1', '2', '3', '4',
            '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k'};
    Random random = new Random(new Date().getTime());
    StringBuilder sb = new StringBuilder(len);
    for (int i = 0; i < len; i++) {
      sb.append(table[random.nextInt(table.length)]);
    }
    return sb.toString();
  }

  /**
   * Первичная авторизация. Создание кода авторизации со сроком "протухания" 30 сек
   *
   * @param I_S_code
   * @param user
   * @param password
   * @return
   */
  public UserTokenInfo authenticate(String I_S_code, String user, String password) {
    UserAccount userAccount = userAccountService.getUser(user);
    if (userAccount == null || !userAccount.getPassword().equals(password)) {
      return null;
    }
    UserTokenInfo userTokenInfo = new UserTokenInfo(I_S_code, generateRandomString(64)
            , generateRandomString(64), generateRandomString(64)
            , 30, userAccount, new Date());
    accessCodeToTokenInfo.put(userTokenInfo.accessCode, userTokenInfo);
    return userTokenInfo;
  }

  /**
   * Обмен кода авторизации на токен
   *
   * @param code
   * @return
   */
  public UserTokenInfo exchangeAccessCodeToToken(String code) {
    UserTokenInfo tokenInfo = accessCodeToTokenInfo.get(code);
    if (tokenInfo == null || (tokenInfo.validUntil.getTime() - new Date().getTime()) <= 0) {
      throw new RuntimeException(ERROR_INVALID_GRANT);
    }
    accessCodeToTokenInfo.remove(code);
    tokenInfo.setExpireDate(3600);
    tokenInfo.tokenValidFrom = new Date();

    tokenToTokenInfo.put(tokenInfo.token, tokenInfo);
    refreshTokenToTokenInfo.put(tokenInfo.refreshToken, tokenInfo);

    return tokenInfo;
  }

}
