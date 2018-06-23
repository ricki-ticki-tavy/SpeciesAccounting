package org.ricki.catalog.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.ricki.catalog.entity.UserWebStyle;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;

import javax.inject.Inject;
import javax.inject.Named;
import java.util.List;

@Named
@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class StyleDao {

  @Inject
  SessionFactory sessionFactory;

  public List<UserWebStyle> getList() {
    Session session = sessionFactory.getCurrentSession();
    return session.createQuery("from UserWebStyle order by name").list();
  }

  public UserWebStyle get(long id) {
    Session session = sessionFactory.getCurrentSession();
    return session.get(UserWebStyle.class, id);
  }

  public UserWebStyle save(UserWebStyle entity) {
    Session session = sessionFactory.getCurrentSession();
    session.saveOrUpdate(entity);
    return entity;
  }
}
