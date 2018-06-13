package org.ricki.catalog.dao;

import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.ricki.catalog.entity.Food;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;

import javax.inject.Inject;
import javax.inject.Named;
import java.util.List;

//import javax.persistence.Query;

@Named
@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class FoodDao {

  //  @PersistenceContext
  @Inject
  private SessionFactory sessionFactory;

  public List<Food> getList() {
    Query q = sessionFactory.getCurrentSession().createQuery("from Food order by name");
    return q.list();
  }
}
