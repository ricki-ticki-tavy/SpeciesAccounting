package org.ricki.catalog.web.abstracts.form.list;

import com.vaadin.navigator.Navigator;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.ui.*;
import org.ricki.catalog.web.abstracts.form.common.IdentifiedForm;

public abstract class BaseForm extends GridLayout implements View, IdentifiedForm {

  public static final String FORM_CONTAINER_BASE_STYLE = "base_form_content";

  protected Navigator navigator;
  private Layout navigatorBar;
  private Layout mainLayout;
  protected boolean selectionMode = false;
  protected UI mainUi;

  public abstract Layout buildContent();

  protected void setSelectionMode() {
    selectionMode = true;
  }

  public void injectUI(UI mainUi, Navigator navigator, Layout navigatorLayout) {
    this.navigator = navigator;
    this.navigatorBar = navigatorLayout;
    this.mainUi = mainUi;

  }

  public BaseForm() {
    super();
    setSizeFull();
    mainLayout = buildContent();
    mainLayout.addStyleName(FORM_CONTAINER_BASE_STYLE);
  }

  public abstract void onOpen(ViewChangeListener.ViewChangeEvent event);

  @Override
  public final void enter(ViewChangeListener.ViewChangeEvent event) {
    setColumns(1);
    setRows(100);

    removeAllComponents();

    Label captionLabel = new Label(getPageCaption());
    captionLabel.setSizeFull();
    HorizontalLayout captionLayout = new HorizontalLayout(captionLabel);
    captionLayout.setComponentAlignment(captionLabel, Alignment.MIDDLE_CENTER);
    captionLayout.addStyleName("base_form_title");
    HorizontalLayout titlePanel = new HorizontalLayout(captionLayout);
    titlePanel.setWidth(100, Unit.PERCENTAGE);
    titlePanel.setHeight(40, Unit.PIXELS);

    mainLayout.setSizeFull();

    if (!selectionMode) {
      addComponent(navigatorBar, 0, 0, 0, 0);
      setComponentAlignment(navigatorBar, Alignment.TOP_LEFT);
      addComponent(titlePanel, 0, 1, 0, 1);
      setComponentAlignment(titlePanel, Alignment.MIDDLE_CENTER);
    }

    addComponent(mainLayout, 0, 2, 0, 99);
    setComponentAlignment(mainLayout, Alignment.MIDDLE_CENTER);

    setSpacing(false);
    setMargin(false);

    onOpen(event);
  }
}
