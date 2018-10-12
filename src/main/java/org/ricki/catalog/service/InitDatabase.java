package org.ricki.catalog.service;

import com.vaadin.spring.annotation.UIScope;
import org.hibernate.SessionFactory;
import org.ricki.catalog.web.page.action.service.AnActionServiceI;
import org.ricki.catalog.web.page.specie.service.PoisonLevelServiceI;
import org.ricki.catalog.web.page.specie.service.SpecieClassServiceI;
import org.ricki.catalog.web.page.styles.entity.UserWebStyle;
import org.ricki.catalog.web.page.styles.service.StyleServiceI;
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
  StyleServiceI styleService;

  @Inject
  AnActionServiceI anActionService;

  @Inject
  UserAccountService userAccountService;

  @Inject
  SpecieClassServiceI specieTypeService;

  @Inject
  PoisonLevelServiceI poisonLevelService;

  public void init() {
    Map<SystemStyleEnum, UserWebStyle> systemStyles = styleService.initSystemStyles();
    anActionService.initSystemActions(systemStyles);
    userAccountService.initSystemActions(systemStyles);
    specieTypeService.initSystemActions(systemStyles);
    poisonLevelService.initSystemActions(systemStyles);

  }
}
