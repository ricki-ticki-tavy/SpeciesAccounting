package org.ricki.catalog.web.page.styles.service;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.ricki.catalog.web.page.styles.entity.UserWebStyle;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;

import javax.inject.Inject;
import javax.inject.Named;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Named
@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class StyleDao {

  @Inject
  SessionFactory sessionFactory;

  public List<UserWebStyle> getList() {
    Session session = sessionFactory.getCurrentSession();
    return session.createQuery("from UserWebStyle order by name").list();
  }

  public UserWebStyle get(long id) {
    Session session = sessionFactory.getCurrentSession();
    return session.get(UserWebStyle.class, id);
  }

  public UserWebStyle save(UserWebStyle entity) {
    Session session = sessionFactory.getCurrentSession();
    session.saveOrUpdate(entity);
    return entity;
  }

  public void remove(UserWebStyle entity) {
    Session session = sessionFactory.getCurrentSession();
    session.remove(entity);
  }

  public void remove(long id) {
    Session session = sessionFactory.getCurrentSession();
    UserWebStyle uws = session.load(UserWebStyle.class, id);
    remove(uws);
  }

  public UserWebStyle findByName(String name) {
    Session session = sessionFactory.getCurrentSession();
    List<UserWebStyle> userWebStyleList = session
            .createQuery("from UserWebStyle where name=:name")
            .setParameter("name", name)
            .list();
    if (userWebStyleList != null && userWebStyleList.size() > 0) {
      return userWebStyleList.get(0);
    } else {
      return null;
    }
  }

  public Map<SystemStyleEnum, UserWebStyle> initSystemStyles() {
    Session session = sessionFactory.getCurrentSession();
    UserWebStyle styleNone;
    UserWebStyle styleRed;
    UserWebStyle styleGrey;
    UserWebStyle styleLightGreen;
    UserWebStyle styleYellow;
    UserWebStyle styleOrange;

    if ((Long) (session.createQuery("select count(*) from UserWebStyle").uniqueResult()) == 0) {
      styleNone = new UserWebStyle(SystemStyleEnum.NONE.getCaption(), SystemStyleEnum.NONE.getName(), "Не изменяет ничего", "", true);
      session.save(styleNone);
      styleRed = new UserWebStyle(SystemStyleEnum.RED.getCaption(), SystemStyleEnum.RED.getName(), "Не изменяет ничего", "background: red", true);
      session.save(styleRed);

      styleGrey = new UserWebStyle(SystemStyleEnum.GRAY.getCaption(), SystemStyleEnum.GRAY.getName(), "Не изменяет ничего", "background: grey", true);
      session.save(styleGrey);

      styleLightGreen = new UserWebStyle(SystemStyleEnum.LIGHT_GREEN.getCaption(), SystemStyleEnum.LIGHT_GREEN.getName(), "Не изменяет ничего", "background: lightGreen", true);
      session.save(styleLightGreen);

      styleYellow = new UserWebStyle(SystemStyleEnum.YELLOW.getCaption(), SystemStyleEnum.YELLOW.getName(), "Не изменяет ничего", "background: yellow", true);
      session.save(styleYellow);

      styleOrange = new UserWebStyle(SystemStyleEnum.ORANGE.getCaption(), SystemStyleEnum.ORANGE.getName(), "Не изменяет ничего", "background: orange", true);
      session.save(styleOrange);

      session.flush();
    } else {
      styleNone = findByName(SystemStyleEnum.NONE.getName());
      styleRed = findByName(SystemStyleEnum.RED.getName());
      styleGrey = findByName(SystemStyleEnum.GRAY.getName());
      styleLightGreen = findByName(SystemStyleEnum.LIGHT_GREEN.getName());
      styleYellow = findByName(SystemStyleEnum.YELLOW.getName());
      styleOrange = findByName(SystemStyleEnum.ORANGE.getName());

    }

    Map<SystemStyleEnum, UserWebStyle> systemStyles = new HashMap<>();
    systemStyles.put(SystemStyleEnum.NONE, styleNone);
    systemStyles.put(SystemStyleEnum.RED, styleRed);
    systemStyles.put(SystemStyleEnum.GRAY, styleGrey);
    systemStyles.put(SystemStyleEnum.LIGHT_GREEN, styleLightGreen);
    systemStyles.put(SystemStyleEnum.YELLOW, styleYellow);
    systemStyles.put(SystemStyleEnum.ORANGE, styleOrange);

    return systemStyles;
  }
}