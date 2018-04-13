package org.ricki.catalog.web.page.styles;

import com.vaadin.event.selection.SelectionEvent;
import com.vaadin.navigator.ViewChangeListener;
import org.ricki.catalog.entity.UserWebStyle;
import org.ricki.catalog.service.StyleService;
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
public class StyleView extends BaseListForm<UserWebStyle> {

  @Inject
  StyleService styleService;

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

}
