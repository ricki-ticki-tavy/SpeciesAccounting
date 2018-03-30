package org.ricki.catalog.web.page.actions;

import com.vaadin.ui.Layout;
import com.vaadin.ui.VerticalLayout;
import org.ricki.catalog.entity.AnAction;
import org.ricki.catalog.service.AnActionService;
import org.ricki.catalog.web.abstracts.form.BaseForm;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;

import javax.inject.Inject;
import javax.inject.Named;

/**
 * Стили для вывода разных ячеек
 */
@Named
@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class ActionView extends BaseForm {

  @Inject
  AnActionService actionService;

  ActionListGrid grid;

  @Override
  public String getPageId() {
    return "actions";
  }

  @Override
  public String getPageCaption() {
    return "Действия";
  }

  @Override
  public Layout buildContent() {
    VerticalLayout content = new VerticalLayout();
    content.setSizeFull();
    content.setId("StylesViewContent");
    setCaption(getPageCaption());

    grid = new ActionListGrid();
    grid.initGrid(AnAction.class);
    grid.setSizeFull();
    content.addComponent(grid);

    return content;
  }

  @Override
  public void onOpen() {
    grid.setItems(actionService.getActionsList());
  }

}
