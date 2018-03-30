package org.ricki.catalog.web.abstracts.form;

import com.vaadin.navigator.Navigator;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Layout;
import com.vaadin.ui.MenuBar;
import com.vaadin.ui.VerticalLayout;

public abstract class BaseForm extends VerticalLayout implements View, PageIdentified {
  protected Navigator navigator;
  private MenuBar navigatorBar;
  private Layout mainLayout;

  public abstract Layout buildContent();

//  private BaseForm() {
//    throw new RuntimeException("Unsupported operation");
//  }
//

  public void injectNavigator(Navigator navigator, MenuBar navigatorLayout) {
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

  public abstract void onOpen();

  @Override
  public final void enter(ViewChangeListener.ViewChangeEvent event) {
    removeAllComponents();
    addComponent(navigatorBar);
    addComponent(mainLayout);
    setComponentAlignment(navigatorBar, Alignment.TOP_CENTER);
    setComponentAlignment(mainLayout, Alignment.MIDDLE_CENTER);
    setSpacing(false);
    setExpandRatio(navigatorBar, 1);
    setExpandRatio(mainLayout, 100);
    onOpen();
  }
}
