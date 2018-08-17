package org.ricki.catalog.web.page.food.service;

import com.vaadin.spring.annotation.UIScope;
import org.ricki.catalog.service.base.BaseService;
import org.ricki.catalog.web.page.food.entity.Food;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;

import javax.inject.Inject;
import javax.inject.Named;
import javax.transaction.Transactional;
import java.util.List;

@Named
@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
@Transactional
@UIScope
public class FoodService implements BaseService<Food> {

  @Inject
  FoodDao foodDao;

  public List<Food> getList() {
    return foodDao.getList();
  }

  @Override
  public Food get(long id) {
    return foodDao.get(id);
  }

  @Override
  public Food create() {
    return new Food();
  }

  @Override
  public Food save(Food entity) {
    return foodDao.save(entity);
  }

  @Override
  public void remove(long id) {
    foodDao.remove(id);
  }

  @Override
  public void remove(Food entity) {
    foodDao.remove(entity);
  }

  @Override
  public Class getEntity() {
    return Food.class;
  }
}
