package org.ricki.catalog.web.page.box.forms;

import com.vaadin.event.selection.SelectionEvent;
import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.ui.Button;
import org.ricki.catalog.web.abstracts.component.grid.MetadataGrid;
import org.ricki.catalog.web.abstracts.form.list.BaseListForm;
import org.ricki.catalog.web.page.box.entity.Box;
import org.ricki.catalog.web.page.box.service.BoxServiceI;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;

import javax.inject.Inject;
import javax.inject.Named;

/**
 * Оборудование
 */
@Named
@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class BoxListForm extends BaseListForm<Box> {

  @Inject
  BoxServiceI boxService;

  @Override
  public String getPageId() {
    return "boxes";
  }

  @Override
  public String getPageCaption() {
    return "Террариумы";
  }

  @Override
  public MetadataGrid buildGrid() {
    BoxListGrid grid = new BoxListGrid();
    grid.initGrid(Box.class);
    return grid;
  }

  @Override
  public void recordSelected(SelectionEvent event) {
    editBtn.setEnabled(event.getFirstSelectedItem().isPresent());
  }

  @Override
  public void loadList() {
    grid.setItems(boxService.getList());
  }

  @Override
  public void onOpen(ViewChangeListener.ViewChangeEvent event) {
    super.onOpen(event);
  }

  @Override
  public void onNewRecord(Button.ClickEvent event) {
    BoxEditForm editForm = new BoxEditForm();
    editForm.setParentListForm(this);
    mainUi.addWindow(editForm);
  }

  @Override
  public void onEditRecord(Box entity) {

//    Object[] recs = grid.getSelectedItems().toArray();
//    if (recs.length > 0) {
//      long id = ((AnAction) recs[0]).getId();
//
//      ActionEditForm editForm = new ActionEditForm();
//      editForm.setParentListForm(this);
//      AnAction anAction = editForm.load(id);
//      if (anAction != null) {
//        editForm.fillForm();
//        mainUi.addWindow(editForm);
//      } else {
//        editForm.close();
//      }
//    }
  }

  @Override
  public void onRemoveRecord(Box entity) {
    Object[] recs = grid.getSelectedItems().toArray();
    if (recs.length > 0) {
      long id = ((Box) recs[0]).getId();
      boxService.remove(id);
    }
  }


}
