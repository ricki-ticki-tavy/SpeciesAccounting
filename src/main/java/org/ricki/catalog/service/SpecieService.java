package org.ricki.catalog.service;

import com.vaadin.spring.annotation.UIScope;
import org.ricki.catalog.dao.SpecieDao;
import org.ricki.catalog.entity.Specie;
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
public class SpecieService {

  @Inject
  SpecieDao specieDao;

  public List<Specie> getList() {
    return specieDao.getList();
  }
}
