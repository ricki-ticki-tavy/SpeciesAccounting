package org.ricki.catalog.web.page.specie.service;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.ricki.catalog.web.page.specie.entity.AggressionLevel;
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
public class AggressionLevelDao {
  @Inject
  SessionFactory sessionFactory;

  public List<AggressionLevel> getList() {
    Session session = sessionFactory.getCurrentSession();
    return session.createQuery("from AggressionLevel order by name").list();
  }

  public AggressionLevel get(long id) {
    Session session = sessionFactory.getCurrentSession();
    return session.get(AggressionLevel.class, id);
  }

  public AggressionLevel save(AggressionLevel entity) {
    Session session = sessionFactory.getCurrentSession();
    session.saveOrUpdate(entity);
    return entity;
  }

  public void remove(AggressionLevel entity) {
    Session session = sessionFactory.getCurrentSession();
    session.remove(entity);
  }

  public void remove(long id) {
    Session session = sessionFactory.getCurrentSession();
    AggressionLevel uws = session.load(AggressionLevel.class, id);
    remove(uws);
  }

  public AggressionLevel findByName(String name) {
    throw new RuntimeException("not implemented");
  }

  public List<AggressionLevel> getList(String filter) {
    Session session = sessionFactory.getCurrentSession();
    return session.createQuery("from AggressionLevel " + (filter == null ? "" : filter) + "order by name").list();
  }

  public void initSystemActions(Map<SystemStyleEnum, UserWebStyle> systemStyles) {
    Session session = sessionFactory.getCurrentSession();
    if ((Long) (session.createQuery("select count(*) from PoisonLevel").uniqueResult()) == 0) {
      session.save(new AggressionLevel("Нет", systemStyles.get(SystemStyleEnum.NONE), 0));
      session.save(new AggressionLevel("Очень низкий", systemStyles.get(SystemStyleEnum.LIGHT_GREEN), 10));
      session.save(new AggressionLevel("Низкий", systemStyles.get(SystemStyleEnum.LIGHT_GREEN), 20));
      session.save(new AggressionLevel("Средний", systemStyles.get(SystemStyleEnum.YELLOW), 30));
      session.save(new AggressionLevel("Выше среднего", systemStyles.get(SystemStyleEnum.ORANGE), 40));
      session.save(new AggressionLevel("Высокий", systemStyles.get(SystemStyleEnum.NONE), 50));
      session.save(new AggressionLevel("ВЫСШИЙ!", systemStyles.get(SystemStyleEnum.NONE), 100));
      session.flush();
    }
  }


}
