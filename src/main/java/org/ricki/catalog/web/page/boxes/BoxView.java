package org.ricki.catalog.web.page.boxes;

import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.ui.Layout;
import com.vaadin.ui.VerticalLayout;
import org.ricki.catalog.entity.Box;
import org.ricki.catalog.service.BoxService;
import org.ricki.catalog.web.abstracts.form.BaseForm;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;

import javax.inject.Inject;
import javax.inject.Named;

/**
 * Оборудование
 */
@Named
@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class BoxView extends BaseForm {

  @Inject
  BoxService boxService;

  BoxListGrid grid;

  @Override
  public String getPageId() {
    return "boxes";
  }

  @Override
  public String getPageCaption() {
    return "Террариумы";
  }

  @Override
  public Layout buildContent() {
    VerticalLayout content = new VerticalLayout();
    content.setSizeFull();
    setCaption(getPageCaption());

    grid = new BoxListGrid();
    grid.initGrid(Box.class);
    grid.setSizeFull();
    content.addComponent(grid);

    return content;
  }

  @Override
  public void onOpen(ViewChangeListener.ViewChangeEvent event) {
    grid.setItems(boxService.getList());

  }

}
