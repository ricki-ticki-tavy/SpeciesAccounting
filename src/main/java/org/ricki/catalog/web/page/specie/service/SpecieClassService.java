package org.ricki.catalog.web.page.specie.service;

import com.vaadin.spring.annotation.UIScope;
import org.ricki.catalog.service.base.BaseNamedEntityFIlteredSelectorService;
import org.ricki.catalog.web.page.specie.entity.SpecieClass;
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
public class SpecieClassService implements BaseNamedEntityFIlteredSelectorService<SpecieClass> {

  @Inject
  SpecieClassDao specieClassDao;

  public List<SpecieClass> getList() {
    return specieClassDao.getList();
  }

  @Override
  public SpecieClass get(long id) {
    return specieClassDao.get(id);
  }

  @Override
  public SpecieClass save(SpecieClass entity) {
    return specieClassDao.save(entity);
  }

  @Override
  public SpecieClass create() {
    return new SpecieClass();
  }

  @Override
  public void remove(long id) {
    specieClassDao.remove(id);
  }

  @Override
  public void remove(SpecieClass entity) {
    specieClassDao.remove(entity);
  }

  @Override
  public Class getEntity() {
    return SpecieClass.class;
  }

  @Override
  public SpecieClass findByName(String name) {
    return specieClassDao.findByName(name);
  }

  @Override
  public List<SpecieClass> getList(String filter) {
    return null;
  }

  public void initSystemActions(Map<SystemStyleEnum, UserWebStyle> systemStyles) {
    specieClassDao.initSystemActions(systemStyles);
  }

}
