package org.ricki.catalog.service;

import com.vaadin.spring.annotation.UIScope;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.ricki.catalog.entity.AggressionLevel;
import org.ricki.catalog.entity.SpecieClass;
import org.ricki.catalog.entity.UserAccount;
import org.ricki.catalog.web.page.actions.entity.ActionResult;
import org.ricki.catalog.web.page.actions.entity.AnAction;
import org.ricki.catalog.web.page.styles.entity.UserWebStyle;
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
      UserWebStyle styleNone = new UserWebStyle("Обычный", "none", "Не изменяет ничего", "", true);
      session.save(styleNone);
      UserWebStyle styleRed = new UserWebStyle("Красный фон", "red", "Не изменяет ничего", "background: red", true);
      session.save(styleRed);

      UserWebStyle styleGrey = new UserWebStyle("Серый фон", "red", "Не изменяет ничего", "background: grey", true);
      session.save(styleGrey);

      UserWebStyle styleLightGreen = new UserWebStyle("Светло зеленый фон", "lightGreen", "Не изменяет ничего", "background: lightGreen", true);
      session.save(styleLightGreen);

      UserWebStyle styleYellow = new UserWebStyle("Желтый фон", "yellow", "Не изменяет ничего", "background: yellow", true);
      session.save(styleYellow);

      UserWebStyle styleOrange = new UserWebStyle("Оранжевый фон", "orange", "Не изменяет ничего", "background: orange", true);
      session.save(styleOrange);

      AnAction feeding = new AnAction("Кормление", "Кормление ВСЕХ особей в контейнере", styleYellow, true, AnAction.ActionRecipient.BOX);

      feeding.getAvailableResults().add(new ActionResult("Съедено", styleLightGreen, feeding));
      feeding.getAvailableResults().add(new ActionResult("Отказ", styleRed, feeding));
      feeding.getAvailableResults().add(new ActionResult("Забрано", styleRed, feeding));

      session.saveOrUpdate(feeding);

      session.save(new UserAccount("root", "system account", "root", false, ""));

      session.save(new SpecieClass("Паук"));
      session.save(new SpecieClass("Скорпион"));
      session.save(new SpecieClass("Таракан"));
      session.save(new SpecieClass("Личинки"));
      session.save(new SpecieClass("Ящерица"));

      session.save(new AggressionLevel("Нет", styleNone, 0));
      session.save(new AggressionLevel("Очень низкий", styleLightGreen, 10));
      session.save(new AggressionLevel("Низкий", styleLightGreen, 20));
      session.save(new AggressionLevel("Средний", styleYellow, 30));
      session.save(new AggressionLevel("Выше среднего", styleOrange, 40));
      session.save(new AggressionLevel("Высокий", styleNone, 50));
      session.save(new AggressionLevel("ВЫСШИЙ!", styleNone, 100));
      session.flush();

    }
  }
}
