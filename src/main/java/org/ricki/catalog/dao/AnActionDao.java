package org.ricki.catalog.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.ricki.catalog.entity.AnAction;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;

import javax.inject.Inject;
import javax.inject.Named;
import java.util.List;

@Named
@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class AnActionDao {

  @Inject
  SessionFactory sessionFactory;

  public List<AnAction> getActionsList() {
    Session session = sessionFactory.getCurrentSession();
    return session.createQuery("from AnAction order by name").list();
  }
}
