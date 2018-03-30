package org.ricki.catalog.dao;

import org.hibernate.SessionFactory;
import org.ricki.catalog.entity.Food;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;

import javax.inject.Inject;
import javax.inject.Named;
import javax.persistence.Query;
import java.util.List;

@Named
@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class FoodDao {

  //  @PersistenceContext
  @Inject
  private SessionFactory sessionFactory;

  public List<Food> getAllFoods() {
    Query q = sessionFactory.getCurrentSession().createQuery("from Food order by name", Food.class);
    return q.getResultList();
  }
}
