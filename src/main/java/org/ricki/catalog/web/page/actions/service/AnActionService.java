package org.ricki.catalog.web.page.actions.service;

import com.vaadin.spring.annotation.UIScope;
import org.ricki.catalog.service.base.BaseNamedEntityService;
import org.ricki.catalog.web.page.actions.entity.AnAction;
import org.ricki.catalog.web.page.styles.entity.UserWebStyle;
import org.ricki.catalog.web.page.styles.service.SystemStyleEnum;
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
public class AnActionService implements BaseNamedEntityService<AnAction> {

  @Inject
  AnActionDao anActionDao;

  public List<AnAction> getList() {
    return anActionDao.getList();
  }

  @Override
  public AnAction get(long id) {
    return anActionDao.get(id);
  }

  @Override
  public AnAction save(AnAction entity) {
    return anActionDao.save(entity);
  }

  @Override
  public AnAction create() {
    return new AnAction();
  }

  @Override
  public void remove(long id) {
    anActionDao.remove(id);
  }

  @Override
  public void remove(AnAction entity) {
    anActionDao.remove(entity);
  }

  @Override
  public Class getEntity() {
    return AnAction.class;
  }

  @Override
  public AnAction findByName(String name) {
    return anActionDao.findByName(name);
  }

  public void initSystemActions(Map<SystemStyleEnum, UserWebStyle> systemStyles) {
    anActionDao.initSystemActions(systemStyles);
  }
}
