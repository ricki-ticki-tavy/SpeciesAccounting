package org.ricki.catalog.web.page.specie.service;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.ricki.catalog.web.page.specie.entity.SpecieClass;
import org.ricki.catalog.web.page.styles.entity.UserWebStyle;
import org.ricki.catalog.web.page.styles.service.SystemStyleEnum;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;

import javax.inject.Inject;
import javax.inject.Named;
import java.util.List;
import java.util.Map;


@Named
@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class SpecieClassDao {
  @Inject
  SessionFactory sessionFactory;

  public List<SpecieClass> getList() {
    Session session = sessionFactory.getCurrentSession();
    return session.createQuery("from SpecieClass order by name").list();
  }

  public SpecieClass get(long id) {
    Session session = sessionFactory.getCurrentSession();
    return session.get(SpecieClass.class, id);
  }

  public SpecieClass save(SpecieClass entity) {
    Session session = sessionFactory.getCurrentSession();
    session.saveOrUpdate(entity);
    return entity;
  }

  public void remove(SpecieClass entity) {
    Session session = sessionFactory.getCurrentSession();
    session.remove(entity);
  }

  public void remove(long id) {
    Session session = sessionFactory.getCurrentSession();
    SpecieClass uws = session.load(SpecieClass.class, id);
    remove(uws);
  }

  public SpecieClass findByName(String name) {
    throw new RuntimeException("not implemented");
  }

  public List<SpecieClass> getList(String filter) {
    Session session = sessionFactory.getCurrentSession();
    return session.createQuery("from SpecieClass " + (filter == null ? "" : filter) + "order by name").list();
  }

  public void initSystemActions(Map<SystemStyleEnum, UserWebStyle> systemStyles) {
    Session session = sessionFactory.getCurrentSession();
    if ((Long) (session.createQuery("select count(*) from SpecieClass").uniqueResult()) == 0) {
      session.save(new SpecieClass("Паук"));
      session.save(new SpecieClass("Скорпион"));
      session.save(new SpecieClass("Таракан"));
      session.save(new SpecieClass("Личинки"));
      session.save(new SpecieClass("Ящерица"));
      session.flush();
    }
  }


}
