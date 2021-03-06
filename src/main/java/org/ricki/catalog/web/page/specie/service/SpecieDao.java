package org.ricki.catalog.web.page.specie.service;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.ricki.catalog.web.page.specie.entity.Specie;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;

import javax.inject.Inject;
import javax.inject.Named;
import java.util.List;

@Named
@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class SpecieDao {

  @Inject
  SessionFactory sessionFactory;

  public List<Specie> getList() {
    Session session = sessionFactory.getCurrentSession();
    return session.createQuery("from Specie order by name").list();
  }

  public Specie get(long id) {
    Session session = sessionFactory.getCurrentSession();
    return session.get(Specie.class, id);
  }

  public Specie save(Specie entity) {
    Session session = sessionFactory.getCurrentSession();
    session.saveOrUpdate(entity);
    return entity;
  }

  public void remove(Specie entity) {
    Session session = sessionFactory.getCurrentSession();
    session.remove(entity);
  }

  public void remove(long id) {
    Session session = sessionFactory.getCurrentSession();
    Specie uws = session.load(Specie.class, id);
    remove(uws);
  }

  public Specie findByName(String name) {
    throw new RuntimeException("not implemented");
  }

  public List<Specie> getList(String filter) {
    Session session = sessionFactory.getCurrentSession();
    return session.createQuery("from Specie " + (filter == null ? "" : filter) + "order by name").list();
  }

}
