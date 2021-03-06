package org.ricki.catalog.web.page.staff;

import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.ui.Layout;
import com.vaadin.ui.VerticalLayout;
import org.ricki.catalog.entity.Staff;
import org.ricki.catalog.service.StaffService;
import org.ricki.catalog.web.abstracts.form.list.BaseForm;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;

import javax.inject.Inject;
import javax.inject.Named;

/**
 * Оборудование
 */
@Named
@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class StaffView extends BaseForm {

  @Inject
  StaffService staffService;

  StaffListGrid grid;

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
    content.setSizeFull();
    setCaption(getPageCaption());

    grid = new StaffListGrid();
    grid.initGrid(Staff.class);
    grid.setSizeFull();
    content.addComponent(grid);

    return content;
  }

  @Override
  public void onOpen(ViewChangeListener.ViewChangeEvent event) {
    grid.setItems(staffService.getList());

  }

}
