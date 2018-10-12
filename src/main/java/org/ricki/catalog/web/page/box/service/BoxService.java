package org.ricki.catalog.web.page.box.service;

import com.vaadin.spring.annotation.UIScope;
import org.ricki.catalog.web.page.box.entity.Box;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;

import javax.inject.Inject;
import javax.inject.Named;
import javax.transaction.Transactional;
import java.util.List;

@Named
@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
@UIScope
@Transactional
public class BoxService implements BoxServiceI {

  @Inject
  BoxDao boxDao;

  public List<Box> getList() {
    return boxDao.getList();
  }

  @Override
  public Box get(long id) {
    return boxDao.get(id);
  }

  @Override
  public Box save(Box entity) {
    return boxDao.save(entity);
  }

  @Override
  public Box create() {
    return new Box();
  }

  @Override
  public void remove(long id) {
    boxDao.remove(id);
  }

  @Override
  public void remove(Box entity) {
    boxDao.remove(entity);
  }

  @Override
  public Class getEntity() {
    return Box.class;
  }

  @Override
  public Box findByName(String name) {
    return boxDao.findByName(name);
  }

  @Override
  public List<Box> getList(String filter) {
    return boxDao.getList(filter);
  }
}
