package org.ricki.catalog.web.page.specie.form;

import com.vaadin.event.selection.SelectionEvent;
import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.ui.Button;
import org.ricki.catalog.service.base.BaseService;
import org.ricki.catalog.web.abstracts.component.grid.MetadataGrid;
import org.ricki.catalog.web.abstracts.form.list.BaseListForm;
import org.ricki.catalog.web.page.specie.entity.Specie;
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
public class SpecieListForm extends BaseListForm<Specie> {

  @Inject
  @Qualifier("specieService")
  BaseService<Specie> specieService;

  @Override
  public String getPageId() {
    return "species";
  }

  @Override
  public String getPageCaption() {
    return "Особи";
  }

  @Override
  public MetadataGrid buildGrid() {
    SpecieListGrid grid = new SpecieListGrid();
    grid.initGrid(Specie.class);
    return grid;
  }

  @Override
  public void recordSelected(SelectionEvent event) {
    editBtn.setEnabled(event.getFirstSelectedItem().isPresent());
  }

  @Override
  public void loadList() {
    grid.setItems(specieService.getList());
  }

  @Override
  public void onOpen(ViewChangeListener.ViewChangeEvent event) {
    super.onOpen(event);
  }

  @Override
  public void onNewRecord(Button.ClickEvent event) {
    SpecieEditForm editForm = new SpecieEditForm();
    editForm.setParentListForm(this);
    mainUi.addWindow(editForm);
  }

  @Override
  public void onEditRecord(Specie entity) {

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
  public void onRemoveRecord(Specie entity) {
//    Object[] recs = grid.getSelectedItems().toArray();
//    if (recs.length > 0) {
//      long id = ((AnAction) recs[0]).getId();
//      specieService.remove(id);
//    }
  }
}
