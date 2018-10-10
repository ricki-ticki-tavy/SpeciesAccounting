package org.ricki.catalog.web.page.user.service;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.ricki.catalog.web.page.styles.entity.UserWebStyle;
import org.ricki.catalog.web.page.styles.service.SystemStyleEnum;
import org.ricki.catalog.web.page.user.entity.UserAccount;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;

import javax.inject.Inject;
import javax.inject.Named;
import java.util.List;
import java.util.Map;

@Named
@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class UserAccountDao {

  @Inject
  SessionFactory sessionFactory;

  public List<UserAccount> getUsersList() {
    Session session = sessionFactory.getCurrentSession();
    return session.createQuery("from UserAccount order by name").list();
  }

  public UserAccount getUser(long id) {
    Session session = sessionFactory.getCurrentSession();
    return session.get(UserAccount.class, id);
  }

  public UserAccount getUser(String name) {
    Session session = sessionFactory.getCurrentSession();
    return (UserAccount) session.createQuery("from UserAccount where name=:name")
            .setParameter("name", name)
            .uniqueResult();
  }

  public void initSystemActions(Map<SystemStyleEnum, UserWebStyle> systemStyles) {
    Session session = sessionFactory.getCurrentSession();
    if ((Long) (session.createQuery("select count(*) from UserAccount").uniqueResult()) == 0) {
      session.save(new UserAccount("root", "system account", "root", false, ""));
      session.flush();
    }
  }


}
