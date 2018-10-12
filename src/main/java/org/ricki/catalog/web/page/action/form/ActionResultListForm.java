package org.ricki.catalog.web.page.action.form;

import com.vaadin.event.selection.SelectionEvent;
import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.ui.Button;
import org.ricki.catalog.service.base.BaseService;
import org.ricki.catalog.web.abstracts.component.grid.MetadataGrid;
import org.ricki.catalog.web.abstracts.form.list.BaseListForm;
import org.ricki.catalog.web.page.action.entity.ActionResult;
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
public class ActionResultListForm extends BaseListForm<ActionResult> {

  @Inject
  @Qualifier("actionResultService")
  BaseService<ActionResult> service;

  @Override
  public String getPageId() {
    return "actionResults";
  }

  @Override
  public String getPageCaption() {
    return "Результаты действий";
  }

  @Override
  public MetadataGrid buildGrid() {
    ActionResultListGrid grid = new ActionResultListGrid();
    grid.initGrid(ActionResult.class);
    return grid;
  }

  @Override
  public void recordSelected(SelectionEvent event) {
    editBtn.setEnabled(event.getFirstSelectedItem().isPresent());
  }

  @Override
  public void loadList() {
    grid.setItems(service.getList());
  }

  @Override
  public void onOpen(ViewChangeListener.ViewChangeEvent event) {
    super.onOpen(event);
  }

  @Override
  public void onNewRecord(Button.ClickEvent event) {
    ActionResultEditForm editForm = new ActionResultEditForm();
    editForm.setParentListForm(this);
    mainUi.addWindow(editForm);
  }

  @Override
  public void onEditRecord(ActionResult entity) {

    Object[] recs = grid.getSelectedItems().toArray();
    if (recs.length > 0) {
      long id = ((ActionResult) recs[0]).getId();

      ActionResultEditForm editForm = new ActionResultEditForm();
      editForm.setParentListForm(this);
      ActionResult anAction = editForm.load(id);
      if (anAction != null) {
        editForm.fillForm();
        mainUi.addWindow(editForm);
      } else {
        editForm.close();
      }
    }
  }

  @Override
  public void onRemoveRecord(ActionResult entity) {
    Object[] recs = grid.getSelectedItems().toArray();
    if (recs.length > 0) {
      long id = ((ActionResult) recs[0]).getId();
      service.remove(id);
    }
  }
}
