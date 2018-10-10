package org.ricki.catalog.web.page.actions.service;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.ricki.catalog.web.page.actions.entity.ActionResult;
import org.ricki.catalog.web.page.actions.entity.AnAction;
import org.ricki.catalog.web.page.styles.entity.UserWebStyle;
import org.ricki.catalog.web.page.styles.service.SystemStyleEnum;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;

import javax.inject.Inject;
import javax.inject.Named;
import java.util.List;
import java.util.Map;

@Named
@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class AnActionDao {

  @Inject
  SessionFactory sessionFactory;

  public List<AnAction> getList() {
    Session session = sessionFactory.getCurrentSession();
    return session.createQuery("from AnAction order by name").list();
  }

  public AnAction get(long id) {
    Session session = sessionFactory.getCurrentSession();
    return session.get(AnAction.class, id);
  }

  public AnAction save(AnAction entity) {
    Session session = sessionFactory.getCurrentSession();
    session.saveOrUpdate(entity);
    return entity;
  }

  public void remove(AnAction entity) {
    Session session = sessionFactory.getCurrentSession();
    session.remove(entity);
  }

  public void remove(long id) {
    Session session = sessionFactory.getCurrentSession();
    AnAction uws = session.load(AnAction.class, id);
    remove(uws);
  }

  public AnAction findByName(String name) {
    Session session = sessionFactory.getCurrentSession();
    List<AnAction> anActionList = session
            .createQuery("from AnAction where name=:name")
            .setParameter("name", name)
            .list();
    if (anActionList != null && anActionList.size() > 0) {
      return anActionList.get(0);
    } else {
      return null;
    }
  }

  public void initSystemActions(Map<SystemStyleEnum, UserWebStyle> systemStyles) {
    Session session = sessionFactory.getCurrentSession();
    if ((Long) (session.createQuery("select count(*) from AnAction").uniqueResult()) == 0) {
      ActionResult ar1 = new ActionResult("Съедено", systemStyles.get(SystemStyleEnum.LIGHT_GREEN));
      ActionResult ar2 = new ActionResult("Отказ", systemStyles.get(SystemStyleEnum.RED));
      ActionResult ar3 = new ActionResult("Забрано", systemStyles.get(SystemStyleEnum.RED));
      session.saveOrUpdate(ar1);
      session.saveOrUpdate(ar2);
      session.saveOrUpdate(ar3);

      AnAction feeding = new AnAction("Кормление", "Кормление ВСЕХ особей в контейнере", systemStyles.get(SystemStyleEnum.YELLOW), true, AnAction.ActionRecipient.BOX);
      feeding.getAvailableResults().add(new ActionResult("Съедено", systemStyles.get(SystemStyleEnum.LIGHT_GREEN), feeding));
      feeding.getAvailableResults().add(new ActionResult("Отказ", systemStyles.get(SystemStyleEnum.RED), feeding));
      feeding.getAvailableResults().add(new ActionResult("Забрано", systemStyles.get(SystemStyleEnum.RED), feeding));
      session.saveOrUpdate(feeding);
      session.flush();
    }
  }


}
