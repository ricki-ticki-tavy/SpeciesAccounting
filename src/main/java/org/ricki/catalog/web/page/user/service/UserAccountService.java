package org.ricki.catalog.web.page.user.service;

import com.vaadin.spring.annotation.UIScope;
import org.ricki.catalog.web.page.styles.entity.UserWebStyle;
import org.ricki.catalog.web.page.styles.service.SystemStyleEnum;
import org.ricki.catalog.web.page.user.entity.UserAccount;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;

import javax.inject.Inject;
import javax.inject.Named;
import javax.transaction.Transactional;
import java.util.List;
import java.util.Map;

@Named
@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
@UIScope
@Transactional
public class UserAccountService {

  @Inject
  UserAccountDao userAccountDao;

  public List<UserAccount> getUsersList() {
    return userAccountDao.getUsersList();
  }

  public UserAccount getUser(long id) {
    return userAccountDao.getUser(id);
  }

  public UserAccount getUser(String name) {
    return userAccountDao.getUser(name);
  }

  public void initSystemActions(Map<SystemStyleEnum, UserWebStyle> systemStyles) {
    userAccountDao.initSystemActions(systemStyles);
  }
}
