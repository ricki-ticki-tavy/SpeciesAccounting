package org.ricki.catalog.system;

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

@Named
@Scope(ConfigurableBeanFactory.SCOPE_SINGLETON)
public class UserTokenHolder {

  @Inject
  UserAccountService userAccountService;

  private Map<String, UserTokenInfo> tokenToTokenInfo = new HashMap<>(100);
  private Map<String, UserTokenInfo> refreshTokenToTokenInfo = new HashMap<>(100);

  private String generateToken() {
    char[] table = new char[]{'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O',
            'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z', '0', '1', '2', '3', '4',
            '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k'};
    Random random = new Random(new Date().getTime());
    StringBuilder sb = new StringBuilder(64);
    for (int i = 0; i < 32; i++) {
      sb.append(table[random.nextInt(table.length)]);
    }
    return sb.toString();
  }

  public UserTokenInfo authenticate(String I_S_code, String user, String password) {
    UserAccount userAccount = userAccountService.getUser(user);
    if (userAccount == null || !userAccount.getPassword().equals(password)) {
      return null;
    }
    UserTokenInfo userTokenInfo = new UserTokenInfo(I_S_code, generateToken(), generateToken(), new Date(new Date().getTime() + 3600000), userAccount);
    tokenToTokenInfo.put(userTokenInfo.token, userTokenInfo);
    refreshTokenToTokenInfo.put(userTokenInfo.refreshToken, userTokenInfo);
    return userTokenInfo;
  }


}
