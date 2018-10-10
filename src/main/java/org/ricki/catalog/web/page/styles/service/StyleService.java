package org.ricki.catalog.web.page.styles.service;

import com.vaadin.spring.annotation.UIScope;
import org.ricki.catalog.service.base.BaseNamedEntityService;
import org.ricki.catalog.web.page.styles.entity.UserWebStyle;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;

import javax.inject.Inject;
import javax.inject.Named;
import javax.transaction.Transactional;
import java.util.List;
import java.util.Map;

@Named
@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
@UIScope
@Transactional
public class StyleService implements BaseNamedEntityService<UserWebStyle> {

  @Inject
  StyleDao styleDao;

  public List<UserWebStyle> getList() {
    return styleDao.getList();
  }

  @Override
  public UserWebStyle get(long id) {
    return styleDao.get(id);
  }

  @Override
  public UserWebStyle save(UserWebStyle entity) {
    return styleDao.save(entity);
  }

  @Override
  public UserWebStyle create() {
    return new UserWebStyle();
  }

  @Override
  public void remove(long id) {
    styleDao.remove(id);
  }

  @Override
  public void remove(UserWebStyle entity) {
    styleDao.remove(entity);
  }

  @Override
  public Class getEntity() {
    return UserWebStyle.class;
  }

  @Override
  public UserWebStyle findByName(String name) {
    return styleDao.findByName(name);
  }

  public Map<SystemStyleEnum, UserWebStyle> initSystemStyles() {
    return styleDao.initSystemStyles();
  }
}
