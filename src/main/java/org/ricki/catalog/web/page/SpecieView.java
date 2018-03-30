package org.ricki.catalog.web.page;

import com.vaadin.ui.Layout;
import com.vaadin.ui.VerticalLayout;
import org.ricki.catalog.web.abstracts.form.BaseForm;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;

import javax.inject.Named;

/**
 * Оборудование
 */
@Named
@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class SpecieView extends BaseForm {

  @Override
  public String getPageId() {
    return "species";
  }

  @Override
  public String getPageCaption() {
    return "Особи";
  }

  @Override
  public Layout buildContent() {
    VerticalLayout content = new VerticalLayout();
    content.setHeight(200, Unit.PIXELS);
    content.setWidth(100, Unit.PERCENTAGE);
    content.setId("SpeciesViewContent");
    setCaption(getPageCaption());

    return content;
  }

  @Override
  public void onOpen() {

  }

}
