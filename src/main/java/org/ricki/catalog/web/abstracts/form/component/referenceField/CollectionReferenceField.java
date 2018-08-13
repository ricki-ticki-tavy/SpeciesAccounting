package org.ricki.catalog.web.abstracts.form.component.referenceField;

import com.vaadin.ui.Button;
import com.vaadin.ui.GridLayout;
import com.vaadin.ui.Layout;
import org.ricki.catalog.entity.abstracts.BaseEntity;
import org.ricki.catalog.web.abstracts.component.grid.MetadataGrid;
import org.ricki.catalog.web.abstracts.component.toolbar.SimpleToolBar;
import org.ricki.catalog.web.abstracts.form.element.annotations.field.collectionReferencs.CollectionReferenceFieldMetadata;

import java.util.List;

public class CollectionReferenceField<E extends BaseEntity> extends GridLayout {

  private Button addBtn, editBtn, removeBtn;
  private SimpleToolBar toolbar;
  private List<E> items;
  private CollectionGrid grid;

  public CollectionReferenceField(Class<E> entityClass, CollectionReferenceFieldMetadata metadata) {
    setSizeFull();
    setRows(100);
    setColumns(1);
    addComponent(buildToolbar(), 0, 0, 0, 0);

    grid = new CollectionGrid();
    grid.initGrid(entityClass, metadata.gridMetadata());
    addComponent(grid, 0, 1, 0, 99);
  }

  private void onNewRecord(Button.ClickEvent event) {

  }

  private void doEditRecord(Button.ClickEvent event) {

  }

  private void askForRemoveRecord(Button.ClickEvent event) {

  }

  private Layout buildToolbar() {
    toolbar = new SimpleToolBar();
    addBtn = toolbar.createAndAddButton("", "toolButtonNew", event -> onNewRecord(event));
    editBtn = toolbar.createAndAddButton("", "toolButtonEdit", event -> doEditRecord(event));
    editBtn.setEnabled(false);
    removeBtn = toolbar.createAndAddButton("", "toolButtonRemove", event -> askForRemoveRecord(event));
    removeBtn.setEnabled(false);
//      addActions(toolbar);
    return toolbar;
  }

  public SimpleToolBar getToolbar() {
    return toolbar;
  }

  class CollectionGrid extends MetadataGrid {
  }
}
