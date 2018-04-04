package org.ricki.catalog.web.page;

import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;

import javax.inject.Named;

@Named
@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class StartView extends BasePlanView {

  @Override
  public String getPageId() {
    return "";
  }

  @Override
  public String getPageCaption() {
    return "Стартовая";
  }


}
