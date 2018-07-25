package org.ricki.catalog.web.page.actions.service;

import com.vaadin.spring.annotation.UIScope;
import org.ricki.catalog.service.base.BaseService;
import org.ricki.catalog.web.page.actions.entity.ActionResult;
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
public class ActionResultService implements BaseService<ActionResult> {

  @Inject
  ActionResultDao actionResultDao;

  public List<ActionResult> getList() {
    return actionResultDao.getList();
  }

  @Override
  public ActionResult get(long id) {
    return actionResultDao.get(id);
  }

  @Override
  public ActionResult save(ActionResult entity) {
    return actionResultDao.save(entity);
  }

  @Override
  public ActionResult create() {
    return new ActionResult();
  }

  @Override
  public void remove(long id) {
    actionResultDao.remove(id);
  }

  @Override
  public void remove(ActionResult entity) {
    actionResultDao.remove(entity);
  }


}
