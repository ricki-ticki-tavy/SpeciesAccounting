package org.ricki.catalog.web;

//import com.vaadin.navigator.Navigator;
//import com.vaadin.server.VaadinRequest;
//import com.vaadin.spring.annotation.SpringUI;
//import com.vaadin.spring.annotation.SpringViewDisplay;
//import com.vaadin.ui.*;
//import org.ricki.catalog.service.FoodService;
//import org.ricki.catalog.web.abstracts.form.BaseForm;
//import org.ricki.catalog.web.page.*;
//import org.springframework.beans.factory.BeanFactory;
//
//import javax.inject.Inject;

import com.vaadin.navigator.Navigator;
import com.vaadin.server.VaadinRequest;
import com.vaadin.spring.annotation.SpringUI;
import com.vaadin.spring.annotation.SpringViewDisplay;
import com.vaadin.ui.*;
import org.ricki.catalog.service.FoodService;
import org.ricki.catalog.web.abstracts.form.BaseForm;
import org.ricki.catalog.web.page.*;
import org.springframework.beans.factory.BeanFactory;

import javax.inject.Inject;

@SpringUI
@SpringViewDisplay
public class MainPageUi extends UI {

  Panel navigatorLayout;

  @Inject
  FoodService foodService;

  @Inject
  BeanFactory beanFactory;

  Navigator navigator;
  MenuBar mainMenu = null;


  private MenuBar.MenuItem addPageToMenu(Class<? extends BaseForm> pageClass, MenuBar.MenuItem parent) {
    BaseForm page = beanFactory.getBean(pageClass);
    page.injectNavigator(navigator, mainMenu);
    if (mainMenu == null) {
      mainMenu = new MenuBar();
      ((Layout) navigatorLayout.getContent()).addComponent(mainMenu);
    }
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


  @Override
  protected void init(VaadinRequest request) {
    navigator = new Navigator(this, this);

    HorizontalLayout nlcont = new HorizontalLayout();
    navigatorLayout = new Panel();
    navigatorLayout.setContent(nlcont);
    navigatorLayout.setId("NavigatorViewContent");
    navigatorLayout.setWidth("100%");
    navigatorLayout.setHeight(40, Unit.PIXELS);
    //setContent(content);   // Attach to the UI

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
