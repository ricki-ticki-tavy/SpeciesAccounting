package org.ricki.catalog.web.page;

import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
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
public class StaffView extends BaseForm {

  @Override
  public String getPageId() {
    return "staff";
  }

  @Override
  public String getPageCaption() {
    return "Оборудование и аксессуары";
  }

  @Override
  public Layout buildContent() {
    VerticalLayout content = new VerticalLayout();
    content.setHeight(200, Unit.PIXELS);
    content.setWidth(100, Unit.PERCENTAGE);
    content.setId("MainViewContent");

    Button button = new Button("!!!!!!!!!!!!!!",
            new Button.ClickListener() {
              @Override
              public void buttonClick(Button.ClickEvent event) {
                navigator.navigateTo("");
              }
            });

    content.addComponent(button);
    content.setComponentAlignment(button, Alignment.MIDDLE_CENTER);
    return content;
  }

  @Override
  public void onOpen() {

  }

}
