package org.ricki.catalog.web;

import com.vaadin.navigator.Navigator;
import com.vaadin.server.Page;
import com.vaadin.server.VaadinRequest;
import com.vaadin.spring.annotation.SpringUI;
import com.vaadin.spring.annotation.SpringViewDisplay;
import com.vaadin.ui.*;
import org.ricki.catalog.web.abstracts.form.BaseForm;
import org.ricki.catalog.web.page.*;
import org.springframework.beans.factory.BeanFactory;

import javax.inject.Inject;

@SpringUI
@SpringViewDisplay
public class MainPageUi extends UI {

  Layout navigatorLayout;
  Layout menuLayout;

  @Inject
  BeanFactory beanFactory;

  Navigator navigator;
  MenuBar mainMenu = null;


  private MenuBar.MenuItem addPageToMenu(Class<? extends BaseForm> pageClass, MenuBar.MenuItem parent) {
    BaseForm page = beanFactory.getBean(pageClass);
    if (mainMenu == null) {
      mainMenu = new MenuBar();
      menuLayout.addComponent(mainMenu);
    }
    page.injectNavigator(navigator, navigatorLayout);
    MenuBar.MenuItem item;
    navigator.addView(page.getPageId(), page);
    MenuBar.Command cmd = (MenuBar.Command) selectedItem -> navigator.navigateTo(page.getPageId());
    if (parent != null) {
      item = parent.addItem(page.getPageCaption(), cmd);
    } else {
      item = mainMenu.addItem(page.getPageCaption(), cmd);
    }
    return item;
  }


  private void addStyles() {
    Page.getCurrent().getStyles().add(".myTopMenu { margin: 0; padding: 0 !important; }");
  }

  @Override
  protected void init(VaadinRequest request) {
    addStyles();
    navigator = new Navigator(this, this);

    navigatorLayout = new VerticalLayout();
    navigatorLayout.addStyleName("myTopMenu");
    navigatorLayout.setId("navigatorLayout");
    navigatorLayout.setWidth("100%");
    navigatorLayout.setHeight(80, Unit.PIXELS);

    HorizontalLayout topLineLayout = new HorizontalLayout();
    topLineLayout.addStyleName("myTopMenu");
    topLineLayout.setHeight(30, Unit.PIXELS);
    topLineLayout.setWidth("100%");
    topLineLayout.addComponent(new Label("asdasdadad"));
    navigatorLayout.addComponent(topLineLayout);

    menuLayout = new HorizontalLayout();
    topLineLayout.addStyleName("myTopMenu");
    menuLayout.setHeight(40, Unit.PIXELS);
    menuLayout.setWidth(100, Unit.PERCENTAGE);

    navigatorLayout.addComponent(menuLayout);

    addPageToMenu(StartView.class, null);

    MenuBar.MenuItem spec = mainMenu.addItem("Особи", null);
    addPageToMenu(SpecieView.class, spec);
    addPageToMenu(SpecieTypeView.class, spec);

    addPageToMenu(PlanView.class, null);
    addPageToMenu(BoxView.class, null);
    addPageToMenu(StaffView.class, null);

    MenuBar.MenuItem finance = mainMenu.addItem("Финансы", null);
    addPageToMenu(SaleView.class, finance);
    addPageToMenu(ExpenditureView.class, finance);


    MenuBar.MenuItem libr = mainMenu.addItem("Справочники", null);

    addPageToMenu(StyleView.class, libr);
    addPageToMenu(org.ricki.catalog.web.page.actions.ActionView.class, libr);
    addPageToMenu(FoodView.class, libr);
  }
}
