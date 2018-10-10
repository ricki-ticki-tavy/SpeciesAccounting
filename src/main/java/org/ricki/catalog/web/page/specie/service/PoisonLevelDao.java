package org.ricki.catalog.web.page.specie.service;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.ricki.catalog.web.page.specie.entity.PoisonLevel;
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
public class PoisonLevelDao {
  @Inject
  SessionFactory sessionFactory;

  public List<PoisonLevel> getList() {
    Session session = sessionFactory.getCurrentSession();
    return session.createQuery("from PoisonLevel order by name").list();
  }

  public PoisonLevel get(long id) {
    Session session = sessionFactory.getCurrentSession();
    return session.get(PoisonLevel.class, id);
  }

  public PoisonLevel save(PoisonLevel entity) {
    Session session = sessionFactory.getCurrentSession();
    session.saveOrUpdate(entity);
    return entity;
  }

  public void remove(PoisonLevel entity) {
    Session session = sessionFactory.getCurrentSession();
    session.remove(entity);
  }

  public void remove(long id) {
    Session session = sessionFactory.getCurrentSession();
    PoisonLevel uws = session.load(PoisonLevel.class, id);
    remove(uws);
  }

  public PoisonLevel findByName(String name) {
    throw new RuntimeException("not implemented");
  }

  public List<PoisonLevel> getList(String filter) {
    Session session = sessionFactory.getCurrentSession();
    return session.createQuery("from PoisonLevel " + (filter == null ? "" : filter) + "order by name").list();
  }

  public void initSystemActions(Map<SystemStyleEnum, UserWebStyle> systemStyles) {
    Session session = sessionFactory.getCurrentSession();
    if ((Long) (session.createQuery("select count(*) from PoisonLevel").uniqueResult()) == 0) {
      session.save(new PoisonLevel("Нет", 0, systemStyles.get(SystemStyleEnum.NONE)));
      session.save(new PoisonLevel("Не болезненно, 1-8 часов", 1, systemStyles.get(SystemStyleEnum.LIGHT_GREEN)));
      session.save(new PoisonLevel("Болезненно, без особого вреда", 2, systemStyles.get(SystemStyleEnum.YELLOW)));
      session.save(new PoisonLevel("Возможны послествия", 3, systemStyles.get(SystemStyleEnum.ORANGE)));
      session.save(new PoisonLevel("Тяжелые последствия", 4, systemStyles.get(SystemStyleEnum.RED)));
      session.flush();
    }
  }


}
