package org.ricki.catalog.system;

import org.ricki.catalog.service.InitDatabase;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.Scope;
import org.springframework.context.event.ContextRefreshedEvent;

import javax.inject.Inject;
import javax.inject.Named;

@Named
@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class AppContextLoader implements ApplicationListener<ContextRefreshedEvent> {

  @Inject
  InitDatabase initDatabase;

  @Override
  public void onApplicationEvent(ContextRefreshedEvent event) {
    initDatabase.init();
  }

}
