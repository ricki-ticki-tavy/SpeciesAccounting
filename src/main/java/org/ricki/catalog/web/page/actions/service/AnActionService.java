package org.ricki.catalog.web.page.actions.service;

import com.vaadin.spring.annotation.UIScope;
import org.ricki.catalog.service.base.BaseService;
import org.ricki.catalog.web.page.actions.entity.AnAction;
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
public class AnActionService implements BaseService<AnAction> {

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


}
