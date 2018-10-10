package org.ricki.catalog.web.page.specie.service;

import com.vaadin.spring.annotation.UIScope;
import org.ricki.catalog.service.base.BaseNamedEntityFIlteredSelectorService;
import org.ricki.catalog.web.page.specie.entity.AggressionLevel;
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
public class AggressionLevelService implements BaseNamedEntityFIlteredSelectorService<AggressionLevel> {

  @Inject
  AggressionLevelDao aggressionLevelDao;

  public List<AggressionLevel> getList() {
    return aggressionLevelDao.getList();
  }

  @Override
  public AggressionLevel get(long id) {
    return aggressionLevelDao.get(id);
  }

  @Override
  public AggressionLevel save(AggressionLevel entity) {
    return aggressionLevelDao.save(entity);
  }

  @Override
  public AggressionLevel create() {
    return new AggressionLevel();
  }

  @Override
  public void remove(long id) {
    aggressionLevelDao.remove(id);
  }

  @Override
  public void remove(AggressionLevel entity) {
    aggressionLevelDao.remove(entity);
  }

  @Override
  public Class getEntity() {
    return AggressionLevel.class;
  }

  @Override
  public AggressionLevel findByName(String name) {
    return aggressionLevelDao.findByName(name);
  }

  @Override
  public List<AggressionLevel> getList(String filter) {
    return null;
  }

  public void initSystemActions(Map<SystemStyleEnum, UserWebStyle> systemStyles) {
    aggressionLevelDao.initSystemActions(systemStyles);
  }

}
