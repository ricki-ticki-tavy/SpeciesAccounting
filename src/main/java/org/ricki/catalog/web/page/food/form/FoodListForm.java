package org.ricki.catalog.web.page.food.form;

import com.vaadin.event.selection.SelectionEvent;
import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.ui.Button;
import org.ricki.catalog.service.base.BaseService;
import org.ricki.catalog.web.abstracts.component.grid.MetadataGrid;
import org.ricki.catalog.web.abstracts.form.list.BaseListForm;
import org.ricki.catalog.web.page.food.entity.Food;
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
public class FoodListForm extends BaseListForm<Food> {

  @Inject
  @Qualifier("foodService")
  BaseService<Food> foodService;

  @Override
  public String getPageId() {
    return "food";
  }

  @Override
  public String getPageCaption() {
    return "Корм";
  }


  @Override
  public MetadataGrid buildGrid() {
    FoodListGrid grid = new FoodListGrid();
    grid.initGrid(Food.class);
    return grid;
  }

  @Override
  public void recordSelected(SelectionEvent event) {
    editBtn.setEnabled(event.getFirstSelectedItem().isPresent());
  }

  @Override
  public void loadList() {
    grid.setItems(foodService.getList());
  }

  @Override
  public void onOpen(ViewChangeListener.ViewChangeEvent event) {
    super.onOpen(event);
  }

  @Override
  public void onNewRecord(Button.ClickEvent event) {
    FoodEditForm editForm = new FoodEditForm();
    editForm.setParentListForm(this);
    mainUi.addWindow(editForm);
  }

  @Override
  public void onEditRecord(Food entity) {

    Object[] recs = grid.getSelectedItems().toArray();
    if (recs.length > 0) {
      long id = ((Food) recs[0]).getId();

      FoodEditForm editForm = new FoodEditForm();
      editForm.setParentListForm(this);
      Food food = editForm.load(id);
      if (food != null) {
        editForm.fillForm();
        mainUi.addWindow(editForm);
      } else {
        editForm.close();
      }
    }
  }

  @Override
  public void onRemoveRecord(Food entity) {
    Object[] recs = grid.getSelectedItems().toArray();
    if (recs.length > 0) {
      long id = ((Food) recs[0]).getId();
      foodService.remove(id);
    }
  }
}
