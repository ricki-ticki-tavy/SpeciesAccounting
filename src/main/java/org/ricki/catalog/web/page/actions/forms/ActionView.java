package org.ricki.catalog.web.page.actions.forms;

import com.vaadin.event.selection.SelectionEvent;
import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.ui.Button;
import org.ricki.catalog.service.base.BaseService;
import org.ricki.catalog.web.abstracts.component.grid.MetadataGrid;
import org.ricki.catalog.web.abstracts.form.list.BaseListForm;
import org.ricki.catalog.web.page.actions.entity.AnAction;
import org.springframework.beans.factory.annotation.Qualifier;
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
  @Qualifier("anActionService")
  BaseService<AnAction> actionService;

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
    super.onOpen(event);
  }

  @Override
  public void onNewRecord(Button.ClickEvent event) {
    ActionEditForm editForm = new ActionEditForm();
    editForm.setParentListForm(this);
    mainUi.addWindow(editForm);
  }

  @Override
  public void onEditRecord(AnAction entity) {

    Object[] recs = grid.getSelectedItems().toArray();
    if (recs.length > 0) {
      long id = ((AnAction) recs[0]).getId();

      ActionEditForm editForm = new ActionEditForm();
      editForm.setParentListForm(this);
      AnAction anAction = editForm.load(id);
      if (anAction != null) {
        editForm.fillForm();
        mainUi.addWindow(editForm);
      } else {
        editForm.close();
      }
    }
  }

  @Override
  public void onRemoveRecord(AnAction entity) {
    Object[] recs = grid.getSelectedItems().toArray();
    if (recs.length > 0) {
      long id = ((AnAction) recs[0]).getId();
      actionService.remove(id);
    }
  }
}
