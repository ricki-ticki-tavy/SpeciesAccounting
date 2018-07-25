package org.ricki.catalog.web.page.actions.service;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.ricki.catalog.web.page.actions.entity.AnAction;
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

  public List<AnAction> getList() {
    Session session = sessionFactory.getCurrentSession();
    return session.createQuery("from AnAction order by name").list();
  }

  public AnAction get(long id) {
    Session session = sessionFactory.getCurrentSession();
    return session.get(AnAction.class, id);
  }

  public AnAction save(AnAction entity) {
    Session session = sessionFactory.getCurrentSession();
    session.saveOrUpdate(entity);
    return entity;
  }

  public void remove(AnAction entity) {
    Session session = sessionFactory.getCurrentSession();
    session.remove(entity);
  }

  public void remove(long id) {
    Session session = sessionFactory.getCurrentSession();
    AnAction uws = session.load(AnAction.class, id);
    remove(uws);
  }

}
