package org.ricki.catalog.web.abstracts.component.grid;

import com.vaadin.ui.Button;
import com.vaadin.ui.Grid;
import org.ricki.catalog.web.abstracts.component.toolbar.SimpleToolBar;

public class MetadataGridForCollection<T> extends MetadataGridWithToolBar<T> {

  Button addButton, removeButton;


  protected void addToCollection() {

  }

  protected void removeFromCollection() {

  }

  public void addUserActions(SimpleToolBar toolBar) {

  }

  @Override
  public void addActions(SimpleToolBar toolBar) {
    addButton = toolBar.createAndAddButton("", "toolButtonPlus", event -> this.addToCollection());
    removeButton = toolBar.createAndAddButton("", "toolButtonMines", event -> this.removeFromCollection());
    removeButton.setEnabled(false);
    addUserActions(toolBar);
  }

  protected void onItemClick(Grid.ItemClick event) {
    removeButton.setEnabled(!metadataGrid.getSelectionModel().getSelectedItems().isEmpty());
  }

  public MetadataGridForCollection(boolean withToolBar) {
    super(withToolBar);
    metadataGrid.addItemClickListener(event -> onItemClick(event));
  }
}
