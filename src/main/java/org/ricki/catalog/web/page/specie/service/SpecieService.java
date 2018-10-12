package org.ricki.catalog.web.page.specie.service;

import com.vaadin.spring.annotation.UIScope;
import org.ricki.catalog.service.base.BaseNamedEntityFIlteredSelectorService;
import org.ricki.catalog.web.page.specie.entity.Specie;
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
public class SpecieService implements BaseNamedEntityFIlteredSelectorService<Specie> {

  @Inject
  SpecieDao specieDao;

  public List<Specie> getList() {
    return specieDao.getList();
  }


  @Override
  public Specie get(long id) {
    return specieDao.get(id);
  }

  @Override
  public Specie save(Specie entity) {
    return specieDao.save(entity);
  }

  @Override
  public Specie create() {
    return new Specie();
  }

  @Override
  public void remove(long id) {
    specieDao.remove(id);
  }

  @Override
  public void remove(Specie entity) {
    specieDao.remove(entity);
  }

  @Override
  public Class getEntity() {
    return Specie.class;
  }

  @Override
  public Specie findByName(String name) {
    return specieDao.findByName(name);
  }

  @Override
  public List<Specie> getList(String filter) {
    return specieDao.getList(filter);
  }
}
