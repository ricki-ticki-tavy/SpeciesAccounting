package org.ricki.catalog.web.page.actions.service;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.ricki.catalog.web.page.actions.entity.ActionResult;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;

import javax.inject.Inject;
import javax.inject.Named;
import java.util.List;

@Named
@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class ActionResultDao {

  @Inject
  SessionFactory sessionFactory;

  public List<ActionResult> getList() {
    Session session = sessionFactory.getCurrentSession();
    return session.createQuery("from ActionResult order by name").list();
  }

  public ActionResult get(long id) {
    Session session = sessionFactory.getCurrentSession();
    return session.get(ActionResult.class, id);
  }

  public ActionResult save(ActionResult entity) {
    Session session = sessionFactory.getCurrentSession();
    session.saveOrUpdate(entity);
    return entity;
  }

  public void remove(ActionResult entity) {
    Session session = sessionFactory.getCurrentSession();
    session.remove(entity);
  }

  public void remove(long id) {
    Session session = sessionFactory.getCurrentSession();
    ActionResult uws = session.load(ActionResult.class, id);
    remove(uws);
  }

}
