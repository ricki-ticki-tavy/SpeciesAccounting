package org.ricki.catalog.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.ricki.catalog.entity.UserAccount;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;

import javax.inject.Inject;
import javax.inject.Named;
import java.util.List;

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
}
