package org.ricki.catalog.web.page.specie.service;

import com.vaadin.spring.annotation.UIScope;
import org.ricki.catalog.service.base.BaseNamedEntityFIlteredSelectorService;
import org.ricki.catalog.web.page.specie.entity.PoisonLevel;
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
public class PoisonLevelService implements BaseNamedEntityFIlteredSelectorService<PoisonLevel> {

  @Inject
  PoisonLevelDao poisonLevelDao;

  public List<PoisonLevel> getList() {
    return poisonLevelDao.getList();
  }

  @Override
  public PoisonLevel get(long id) {
    return poisonLevelDao.get(id);
  }

  @Override
  public PoisonLevel save(PoisonLevel entity) {
    return poisonLevelDao.save(entity);
  }

  @Override
  public PoisonLevel create() {
    return new PoisonLevel();
  }

  @Override
  public void remove(long id) {
    poisonLevelDao.remove(id);
  }

  @Override
  public void remove(PoisonLevel entity) {
    poisonLevelDao.remove(entity);
  }

  @Override
  public Class getEntity() {
    return PoisonLevel.class;
  }

  @Override
  public PoisonLevel findByName(String name) {
    return poisonLevelDao.findByName(name);
  }

  @Override
  public List<PoisonLevel> getList(String filter) {
    return null;
  }

  public void initSystemActions(Map<SystemStyleEnum, UserWebStyle> systemStyles) {
    poisonLevelDao.initSystemActions(systemStyles);
  }

}
