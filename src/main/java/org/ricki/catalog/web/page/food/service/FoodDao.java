package org.ricki.catalog.web.page.food.service;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.ricki.catalog.web.page.food.entity.Food;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;

import javax.inject.Inject;
import javax.inject.Named;
import java.util.List;

@Named
@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class FoodDao {

  //  @PersistenceContext
  @Inject
  private SessionFactory sessionFactory;

  public List<Food> getList() {
    Session session = sessionFactory.getCurrentSession();
    return session.createQuery("from Food order by name").list();
  }

  public Food get(long id) {
    Session session = sessionFactory.getCurrentSession();
    return session.get(Food.class, id);
  }

  public Food save(Food entity) {
    Session session = sessionFactory.getCurrentSession();
    session.saveOrUpdate(entity);
    return entity;
  }

  public void remove(Food entity) {
    Session session = sessionFactory.getCurrentSession();
    session.remove(entity);
  }

  public void remove(long id) {
    Session session = sessionFactory.getCurrentSession();
    Food uws = session.load(Food.class, id);
    remove(uws);
  }

}
