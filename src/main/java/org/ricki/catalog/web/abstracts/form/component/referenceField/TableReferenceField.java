package org.ricki.catalog.web.abstracts.form.component.referenceField;

import com.vaadin.event.selection.SelectionEvent;
import com.vaadin.ui.Button;
import org.ricki.catalog.entity.abstracts.BaseEntity;
import org.ricki.catalog.web.abstracts.component.grid.MetadataGridWithToolBar;
import org.ricki.catalog.web.abstracts.component.toolbar.SimpleToolBar;
import org.ricki.catalog.web.abstracts.form.element.BaseEditForm;
import org.ricki.catalog.web.abstracts.form.element.annotations.field.grid.GridMetadata;
import org.ricki.catalog.web.abstracts.form.element.annotations.field.tableReferencs.TableReferenceFieldMetadata;
import org.ricki.catalog.web.abstracts.form.list.BaseListForm;
import org.springframework.web.context.support.SpringBeanAutowiringSupport;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class TableReferenceField<E> extends MetadataGridWithToolBar<E> {

  protected Button addButton, removeButton;
  protected Class<? extends BaseListForm> selectorForm = null;
  protected Class<? extends BaseEditForm> editorElementForm = null;

  protected void addToCollection() {
    try {
      if (selectorForm == null) {
        if (editorElementForm != null) {

        }
      } else {
        // Селектор есть
        BaseListForm listForm = selectorForm.newInstance();
        SpringBeanAutowiringSupport.processInjectionBasedOnCurrentContext(listForm);
        listForm.showForSelect(entity -> {
          if (entity != null) {
            List items = metadataGrid.getItems();
            if (items == null) {
              items = new ArrayList();
            }
            items.add(entity);
            metadataGrid.setItems(items);
          }
        });

      }
    } catch (IllegalAccessException | InstantiationException ie) {
      throw new RuntimeException(ie);
    }
  }

  protected void removeFromCollection() {
    E entityToRemoveFromList = (E) metadataGrid.getSelectionModel().getSelectedItems().toArray()[0];
    Collection<E> items = metadataGrid.getItems();
    items.remove(entityToRemoveFromList);
    metadataGrid.setItems(items);
    removeButton.setEnabled(false);
  }

  public void addUserActions(SimpleToolBar toolBar) {

  }

  @Override
  public void initGrid(Class<? extends BaseEntity> entityClass, GridMetadata gridMetadata) {
    throw new RuntimeException("use initGrid(BaseEntity, TableReferenceFieldMetadata");
  }

  public void initGrid(Class<? extends BaseEntity> entityClass, TableReferenceFieldMetadata gridMetadata) {
    super.initGrid(entityClass, gridMetadata.gridMetadata());
    if (gridMetadata.entityEditorFormClass() != TableReferenceFieldMetadata.NULL_EDIT_FORM.class) {
      editorElementForm = gridMetadata.entityEditorFormClass();
    }
    if (gridMetadata.entityListFormClass() != TableReferenceFieldMetadata.NULL_LIST_FORM.class) {
      selectorForm = gridMetadata.entityListFormClass();
    }
    metadataGrid.addSelectionListener(event -> onItemClick(event));

  }

  @Override
  public void addActions(SimpleToolBar toolBar) {
    addButton = toolBar.createAndAddButton("", "toolButtonPlus", event -> this.addToCollection());
    removeButton = toolBar.createAndAddButton("", "toolButtonMines", event -> this.removeFromCollection());
    removeButton.setEnabled(false);
    addUserActions(toolBar);
  }

  protected void onItemClick(SelectionEvent event) {
    removeButton.setEnabled(!event.getAllSelectedItems().isEmpty());
  }

  public TableReferenceField(boolean withToolBar) {
    super(withToolBar);
  }

  public void setValue(Collection<E> value) {
    metadataGrid.setItems(value);
  }

  public Collection<E> getValue() {
    return metadataGrid.getItems();
  }
}
