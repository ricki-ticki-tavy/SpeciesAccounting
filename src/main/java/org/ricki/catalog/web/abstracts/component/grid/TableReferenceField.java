package org.ricki.catalog.web.abstracts.component.grid;

import com.vaadin.event.selection.SelectionEvent;
import com.vaadin.event.selection.SelectionListener;
import com.vaadin.ui.Button;
import org.ricki.catalog.entity.abstracts.BaseEntity;
import org.ricki.catalog.web.abstracts.component.toolbar.SimpleToolBar;
import org.ricki.catalog.web.abstracts.form.element.BaseEditForm;
import org.ricki.catalog.web.abstracts.form.element.annotations.field.collectionReferencs.CollectionReferenceFieldMetadata;
import org.ricki.catalog.web.abstracts.form.element.annotations.field.grid.GridMetadata;
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
  }

  public void addUserActions(SimpleToolBar toolBar) {

  }

  @Override
  public void initGrid(Class<? extends BaseEntity> entityClass, GridMetadata gridMetadata) {
    throw new RuntimeException("use initGrid(BaseEntity, CollectionReferenceFieldMetadata");
  }

  public void initGrid(Class<? extends BaseEntity> entityClass, CollectionReferenceFieldMetadata gridMetadata) {
    super.initGrid(entityClass, gridMetadata.gridMetadata());
    if (gridMetadata.entityEditorFormClass() != CollectionReferenceFieldMetadata.NULL_EDIT_FORM.class) {
      editorElementForm = gridMetadata.entityEditorFormClass();
    }
    if (gridMetadata.entityListFormClass() != CollectionReferenceFieldMetadata.NULL_LIST_FORM.class) {
      selectorForm = gridMetadata.entityListFormClass();
    }
  }

  @Override
  public void addActions(SimpleToolBar toolBar) {
    addButton = toolBar.createAndAddButton("", "toolButtonPlus", event -> this.addToCollection());
    removeButton = toolBar.createAndAddButton("", "toolButtonMines", event -> this.removeFromCollection());
    removeButton.setEnabled(false);
    addUserActions(toolBar);
  }

  protected void onItemClick(SelectionEvent event) {
    removeButton.setEnabled(!metadataGrid.getSelectionModel().getSelectedItems().isEmpty());
  }

  public TableReferenceField(boolean withToolBar) {
    super(withToolBar);
//    metadataGrid.addListener()
    metadataGrid.addListener(event -> {
      if (event instanceof SelectionListener) {
        int i = 20;
        i++;
      }
    });
  }

  public void setValue(Collection<E> value) {
    metadataGrid.setItems(value);
  }

  public Collection<E> getValue() {
    return metadataGrid.getItems();
  }
}
