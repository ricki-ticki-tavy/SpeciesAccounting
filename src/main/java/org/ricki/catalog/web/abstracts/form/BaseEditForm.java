package org.ricki.catalog.web.abstracts.form;

import com.vaadin.ui.GridLayout;
import com.vaadin.ui.Window;

import javax.inject.Named;
import javax.transaction.Transactional;

@Transactional
@Named
public class BaseEditForm extends Window {

  public static final String BASE_WINDOW_STYLE = "base_window_form_style";

  public BaseEditForm() {
    setWidth(90, Unit.PERCENTAGE);
    setHeight(70, Unit.PERCENTAGE);
    GridLayout content = new GridLayout(20, 20);
    setContent(content);
    content.addStyleName(BASE_WINDOW_STYLE);
    setModal(true);
  }

}
