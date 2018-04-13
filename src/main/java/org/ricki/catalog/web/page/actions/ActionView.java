package org.ricki.catalog.web.page.actions;

import com.vaadin.event.selection.SelectionEvent;
import com.vaadin.navigator.ViewChangeListener;
import org.ricki.catalog.entity.AnAction;
import org.ricki.catalog.service.AnActionService;
import org.ricki.catalog.web.abstracts.component.grid.MetadataGrid;
import org.ricki.catalog.web.abstracts.form.BaseListForm;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;

import javax.inject.Inject;
import javax.inject.Named;

/**
 * Стили для вывода разных ячеек
 */
@Named
@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class ActionView extends BaseListForm<AnAction> {

  @Inject
  AnActionService actionService;

  @Override
  public String getPageId() {
    return "actions";
  }

  @Override
  public String getPageCaption() {
    return "Действия";
  }

  @Override
  public MetadataGrid buildGrid() {
    ActionListGrid grid = new ActionListGrid();
    grid.initGrid(AnAction.class);
    return grid;
  }

  @Override
  public void recordSelected(SelectionEvent event) {
    editBtn.setEnabled(event.getFirstSelectedItem().isPresent());
  }

  @Override
  public void loadList() {
    grid.setItems(actionService.getList());
  }

  @Override
  public void onOpen(ViewChangeListener.ViewChangeEvent event) {
    loadList();
  }

}
