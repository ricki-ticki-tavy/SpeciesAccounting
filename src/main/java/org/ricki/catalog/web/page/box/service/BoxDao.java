package org.ricki.catalog.web.page.box.service;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.ricki.catalog.web.page.box.entity.Box;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;

import javax.inject.Inject;
import javax.inject.Named;
import java.util.List;

@Named
@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class BoxDao {

  @Inject
  SessionFactory sessionFactory;

  public List<Box> getList() {
    Session session = sessionFactory.getCurrentSession();
    return session.createQuery("from Box order by name").list();
  }

  public Box get(long id) {
    Session session = sessionFactory.getCurrentSession();
    return session.get(Box.class, id);
  }

  public Box save(Box entity) {
    Session session = sessionFactory.getCurrentSession();
    session.saveOrUpdate(entity);
    return entity;
  }

  public void remove(Box entity) {
    Session session = sessionFactory.getCurrentSession();
    session.remove(entity);
  }

  public void remove(long id) {
    Session session = sessionFactory.getCurrentSession();
    Box uws = session.load(Box.class, id);
    remove(uws);
  }

  public Box findByName(String name) {
    throw new RuntimeException("not implemented");
  }

  public List<Box> getList(String filter) {
    Session session = sessionFactory.getCurrentSession();
    return session.createQuery("from Box " + (filter == null ? "" : filter) + "order by name").list();
  }


}
