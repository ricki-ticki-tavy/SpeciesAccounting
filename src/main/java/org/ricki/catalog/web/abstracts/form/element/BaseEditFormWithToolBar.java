package org.ricki.catalog.web.abstracts.form.element;

import com.vaadin.ui.Button;
import com.vaadin.ui.GridLayout;
import com.vaadin.ui.Layout;
import org.ricki.catalog.web.abstracts.component.toolbar.SimpleToolBar;

public abstract class BaseEditFormWithToolBar extends BaseEditForm {

  private Layout formLayout;

  public abstract void addActions(SimpleToolBar toolbar);

  public abstract Layout buildContent();

  protected Button saveButton;

  public BaseEditFormWithToolBar() {
    super();
    GridLayout mainLayout = getRootContent();

    formLayout = buildContent();

    mainLayout.addComponent(buildToolbar(), 0, 1, 0, 1);
    mainLayout.addComponent(formLayout, 0, 2, 0, 99);
  }

  protected Layout buildToolbar() {
    SimpleToolBar toolbar = new SimpleToolBar();
    saveButton = toolbar.createAndAddButton("", "toolButtonOk", null);
    toolbar.createAndAddButton("", "toolButtonCancel", event -> this.close());
    toolbar.addStyleName("base_window_form_toolbar_position_style");
    addActions(toolbar);
    return toolbar;
  }

}
