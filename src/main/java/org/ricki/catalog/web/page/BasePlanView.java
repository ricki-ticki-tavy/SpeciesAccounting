package org.ricki.catalog.web.page;

import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.ui.Layout;
import com.vaadin.ui.VerticalLayout;
import org.ricki.catalog.web.abstracts.form.list.BaseForm;

/**
 * Стили для вывода разных ячеек
 */
public abstract class BasePlanView extends BaseForm {

  @Override
  public String getPageCaption() {
    return "Расписание";
  }

  @Override
  public Layout buildContent() {
    VerticalLayout content = new VerticalLayout();
    content.setHeight(200, Unit.PIXELS);
    content.setWidth(100, Unit.PERCENTAGE);
    content.setId("StylesViewContent");
    setCaption(getPageCaption());

    return content;
  }

  @Override
  public void onOpen(ViewChangeListener.ViewChangeEvent event) {

  }
}
