package org.ricki.catalog.web.abstracts.form;

import com.vaadin.navigator.Navigator;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.ui.*;

public abstract class BaseForm extends GridLayout implements View, PageIdentified {
  protected Navigator navigator;
  private Layout navigatorBar;
  private Layout mainLayout;

  public abstract Layout buildContent();

  public void injectNavigator(Navigator navigator, Layout navigatorLayout) {
    this.navigator = navigator;
    this.navigatorBar = navigatorLayout;
  }

  public BaseForm() {
    super();
    setId("mainView");
    setSizeFull();
    mainLayout = buildContent();
    mainLayout.setId("mainLayout");
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
    HorizontalLayout titlePanel = new HorizontalLayout(captionLayout);
    titlePanel.setWidth(100, Unit.PERCENTAGE);
    titlePanel.setHeight(25, Unit.PIXELS);

    mainLayout.setSizeFull();

    addComponent(navigatorBar, 0, 0, 0, 0);
    addComponent(titlePanel, 0, 1, 0, 1);
    addComponent(mainLayout, 0, 2, 0, 99);

    setComponentAlignment(navigatorBar, Alignment.TOP_LEFT);
    setComponentAlignment(titlePanel, Alignment.MIDDLE_CENTER);
    setComponentAlignment(mainLayout, Alignment.MIDDLE_CENTER);
    setSpacing(false);
    setMargin(false);

    onOpen(event);
  }
}
