package org.ricki.catalog.web.page.styles;

import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.ui.Button;
import org.ricki.catalog.entity.UserWebStyle;
import org.ricki.catalog.service.StyleService;
import org.ricki.catalog.web.abstracts.component.grid.MetadataGrid;
import org.ricki.catalog.web.abstracts.component.toolbar.SimpleToolBar;
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
public class StyleView extends BaseListForm {

  @Inject
  StyleService styleService;

  StyleListGrid grid;

  @Override
  public String getPageId() {
    return "styles";
  }

  @Override
  public String getPageCaption() {
    return "Стили отображения";
  }

  public void newRecord(Button.ClickEvent event) {

  }

  public void editRecord(Button.ClickEvent event) {

  }

  public void removeRecord(Button.ClickEvent event) {

  }

  @Override
  public void addActions(SimpleToolBar toolbar) {

  }

  @Override
  public MetadataGrid buildGrid() {
    grid = new StyleListGrid();
    grid.initGrid(UserWebStyle.class);
    return grid;
  }

  @Override
  public void onOpen(ViewChangeListener.ViewChangeEvent event) {
    grid.setItems(styleService.getList());
  }

}
