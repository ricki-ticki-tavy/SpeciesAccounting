package org.ricki.catalog.service;

import com.vaadin.spring.annotation.UIScope;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.ricki.catalog.web.page.actions.service.AnActionService;
import org.ricki.catalog.web.page.specie.service.PoisonLevelService;
import org.ricki.catalog.web.page.specie.service.SpecieClassService;
import org.ricki.catalog.web.page.styles.entity.UserWebStyle;
import org.ricki.catalog.web.page.styles.service.StyleService;
import org.ricki.catalog.web.page.styles.service.SystemStyleEnum;
import org.ricki.catalog.web.page.user.service.UserAccountService;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;

import javax.inject.Inject;
import javax.inject.Named;
import javax.transaction.Transactional;
import java.util.Map;

@Transactional
@Named
@UIScope
@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class InitDatabase {
  @Inject
  SessionFactory sessionFactory;

  @Inject
  StyleService styleService;

  @Inject
  AnActionService anActionService;

  @Inject
  UserAccountService userAccountService;

  @Inject
  SpecieClassService specieTypeService;

  @Inject
  PoisonLevelService poisonLevelService;

  public void init() {
    Session session = sessionFactory.getCurrentSession();

    Map<SystemStyleEnum, UserWebStyle> systemStyles = styleService.initSystemStyles();
    anActionService.initSystemActions(systemStyles);
    userAccountService.initSystemActions(systemStyles);
    specieTypeService.initSystemActions(systemStyles);
    poisonLevelService.initSystemActions(systemStyles);

  }
}
