package org.ricki.catalog.service;

import com.vaadin.spring.annotation.UIScope;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.ricki.catalog.entity.ActionResult;
import org.ricki.catalog.entity.AnAction;
import org.ricki.catalog.entity.UserAccount;
import org.ricki.catalog.entity.UserWebStyle;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;

import javax.inject.Inject;
import javax.inject.Named;
import javax.transaction.Transactional;

@Transactional
@Named
@UIScope
@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class InitDatabase {
  @Inject
  SessionFactory sessionFactory;

  public void init() {
    Session session = sessionFactory.getCurrentSession();
    if ((Long) (session.createQuery("select count(*) from UserWebStyle").uniqueResult()) == 0) {
      session.save(new UserWebStyle("Обычный", "none", "Не изменяет ничего", "", true));
      UserWebStyle styleRed = new UserWebStyle("Красный фон", "red", "Не изменяет ничего", "background: red", true);
      session.save(styleRed);

      UserWebStyle styleGrey = new UserWebStyle("Серый фон", "red", "Не изменяет ничего", "background: grey", true);
      session.save(styleGrey);

      UserWebStyle styleLightGreen = new UserWebStyle("Светло зеленый фон", "lightGreen", "Не изменяет ничего", "background: lightGreen", true);
      session.save(styleLightGreen);

      UserWebStyle styleYellow = new UserWebStyle("Желтый фон", "yellow", "Не изменяет ничего", "background: yellow", true);
      session.save(styleYellow);

      AnAction feeding = new AnAction("Кормление", "Кормление ВСЕХ особей в контейнере", styleYellow, true, AnAction.ActionRecipient.BOX);

      feeding.getAvailableResults().add(new ActionResult("Съедено", styleLightGreen, feeding));
      feeding.getAvailableResults().add(new ActionResult("Отказ", styleRed, feeding));
      feeding.getAvailableResults().add(new ActionResult("Забрано", styleRed, feeding));

      session.saveOrUpdate(feeding);

      session.save(new UserAccount("root", "system account", "root", false, ""));
      session.flush();

    }
  }
}
