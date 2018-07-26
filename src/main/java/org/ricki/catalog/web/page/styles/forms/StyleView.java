package org.ricki.catalog.web.page.styles.forms;

import com.vaadin.event.selection.SelectionEvent;
import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.ui.Button;
import com.vaadin.ui.UI;
import org.ricki.catalog.service.base.BaseService;
import org.ricki.catalog.web.abstracts.component.grid.MetadataGrid;
import org.ricki.catalog.web.abstracts.form.list.BaseListForm;
import org.ricki.catalog.web.page.styles.entity.UserWebStyle;
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
public class StyleView extends BaseListForm<UserWebStyle> {

  @Inject
  @Qualifier("styleService")
  BaseService<UserWebStyle> styleService;

  @Override
  public String getPageId() {
    return "styles";
  }

  @Override
  public String getPageCaption() {
    return "Стили отображения";
  }

  @Override
  public void recordSelected(SelectionEvent event) {
    editBtn.setEnabled(event.getFirstSelectedItem().isPresent());
  }

  @Override
  public MetadataGrid buildGrid() {
    StyleListGrid grid = new StyleListGrid();
    grid.initGrid(UserWebStyle.class);
    return grid;
  }

  @Override
  public void loadList() {
    grid.setItems(styleService.getList());
  }

  @Override
  public void onOpen(ViewChangeListener.ViewChangeEvent event) {
    super.onOpen(event);
  }

  @Override
  public void onNewRecord(Button.ClickEvent event) {
    StyleEditForm editForm = new StyleEditForm();
    editForm.setParentListForm(this);
    UI.getCurrent().addWindow(editForm);
  }

  @Override
  public void onEditRecord(UserWebStyle entity) {

    Object[] recs = grid.getSelectedItems().toArray();
    if (recs.length > 0) {
      long id = ((UserWebStyle) recs[0]).getId();

      StyleEditForm editForm = new StyleEditForm();
      editForm.setParentListForm(this);
      UserWebStyle userWebStyle = editForm.load(id);
      if (userWebStyle != null) {
        editForm.fillForm();
        UI.getCurrent().addWindow(editForm);
      } else {
        editForm.close();
      }
    }
  }

  @Override
  public void onRemoveRecord(UserWebStyle entity) {
    Object[] recs = grid.getSelectedItems().toArray();
    if (recs.length > 0) {
      long id = ((UserWebStyle) recs[0]).getId();
      styleService.remove(id);
    }
  }

}
