package org.ricki.catalog.web.abstracts.component.grid;

import com.vaadin.ui.GridLayout;
import org.ricki.catalog.entity.abstracts.BaseEntity;
import org.ricki.catalog.web.abstracts.component.toolbar.SimpleToolBar;
import org.ricki.catalog.web.abstracts.form.element.annotations.field.grid.GridMetadata;

public abstract class MetadataGridWithToolBar<T> extends GridLayout {

  protected MetadataGrid metadataGrid;
  protected SimpleToolBar toolBar;
  protected boolean withToolBar;

  public MetadataGridWithToolBar(boolean withToolBar) {
    this.withToolBar = withToolBar;
    metadataGrid = new MetadataGridImpl<T>();
    metadataGrid.setWidth(100, Unit.PERCENTAGE);
    setColumns(1);
    setRows(100);
    if (withToolBar) {
      addComponent(buildToolBar(), 0, 0, 0, 0);
      addComponent(metadataGrid, 0, 1, 0, 99);
    } else {
      addComponent(metadataGrid, 0, 0, 0, 99);
    }
    setMargin(false);
  }

  @Override
  public void setHeight(float height, Unit unit) {
    super.setHeight(height, unit);
    if (withToolBar) {
      metadataGrid.setHeight(height - 40, Unit.PIXELS);
    } else {
      metadataGrid.setHeight(height, Unit.PIXELS);
    }
  }

  public void initGrid(Class<? extends BaseEntity> entityClass) {
    metadataGrid.initGrid(entityClass, null);
  }

  public void initGrid(Class<? extends BaseEntity> entityClass, GridMetadata gridMetadata) {
    metadataGrid.initGrid(entityClass, gridMetadata);
  }

  public void addActions(SimpleToolBar simpleToolBar) {

  }

  private SimpleToolBar buildToolBar() {
    toolBar = new SimpleToolBar();
    toolBar.addStyleName("base_window_form_toolbar_position_style");
    addActions(toolBar);
    return toolBar;
  }

  public class MetadataGridImpl<T> extends MetadataGrid<T> {
  }
}
