package org.ricki.catalog.web.page;

import com.vaadin.ui.Layout;
import com.vaadin.ui.VerticalLayout;
import org.ricki.catalog.web.abstracts.form.BaseForm;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;

import javax.inject.Named;

/**
 * Стили для вывода разных ячеек
 */
@Named
@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class SpecieTypeView extends BaseForm {

  @Override
  public String getPageId() {
    return "specieTypes";
  }

  @Override
  public String getPageCaption() {
    return "Виды и условия содержания";
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
  public void onOpen() {

  }

}
