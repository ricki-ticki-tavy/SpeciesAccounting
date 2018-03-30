package org.ricki.catalog.service;

import com.vaadin.spring.annotation.UIScope;
import org.ricki.catalog.dao.FoodDao;
import org.ricki.catalog.entity.Food;
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
public class FoodService {

  @Inject
  FoodDao foodDao;

  public List<Food> getAllFoods() {
    return foodDao.getAllFoods();
  }
}
