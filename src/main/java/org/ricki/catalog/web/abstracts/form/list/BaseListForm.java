package org.ricki.catalog.web.abstracts.form.list;

import com.vaadin.event.selection.SelectionEvent;
import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.shared.ui.window.WindowMode;
import com.vaadin.ui.*;
import de.steinwedel.messagebox.MessageBox;
import org.ricki.catalog.entity.abstracts.BaseEntity;
import org.ricki.catalog.entity.abstracts.BaseNamedEntity;
import org.ricki.catalog.entity.abstracts.SystemRecordDetectable;
import org.ricki.catalog.web.abstracts.component.grid.MetadataGrid;
import org.ricki.catalog.web.abstracts.component.toolbar.SimpleToolBar;

import java.util.Optional;
import java.util.Set;

public abstract class BaseListForm<E extends BaseEntity> extends BaseForm {
  protected Button addBtn, editBtn, removeBtn, reloadBtn, closeButton, selectOkButton;
  protected MetadataGrid grid;
  protected RecordFromListFormSelectedEvent selectorHandler;
  protected Window formWindow;

  public void addActions(SimpleToolBar toolbar) {
  }

  public void onReloadList() {

  }

  public void onNewRecord(Button.ClickEvent event) {

  }

  public void onEditRecord(E entity) {

  }

  public void onRemoveRecord(E entity) {

  }

  private void internalOnRemoveRecord(E entity) {
    onRemoveRecord(entity);
    loadList();
  }

  public void onRecordAdded(E entity) {
    loadList();
    grid.select(entity);
    grid.markAsDirty();
  }

  @Override
  protected void setSelectionMode() {
    super.setSelectionMode();
    selectOkButton.setVisible(true);
    closeButton.setVisible(true);
  }

  public void showForSelect(RecordFromListFormSelectedEvent selectorHandler) {
    this.selectorHandler = selectorHandler;
    formWindow = new Window();
    formWindow.setResizable(false);
//    formWindow.setCaption(getCaption());
    formWindow.setModal(true);
    formWindow.setClosable(false);
    formWindow.setCaption(null);
    formWindow.setWindowMode(WindowMode.MAXIMIZED);
    HorizontalLayout rootLayout = new HorizontalLayout();
    rootLayout.setSizeFull();
    rootLayout.addComponent(this);
    formWindow.setContent(rootLayout);
    setSelectionMode();
    enter(null);
    UI.getCurrent().addWindow(formWindow);
  }

  public void onRecordUpdated(E entity) {
//    grid.getDataProvider().refreshItem(entity);
    loadList();
    grid.select(entity);
    //   grid.markAsDirty();
  }

  public void onRecordDeleted(E entity) {

  }

  private void askForRemoveRecord(Button.ClickEvent event) {
    Set objs = grid.getSelectedItems();
    if (objs.size() == 0) {
      MessageBox.createError().withCaption("Удаление")
              .withMessage("Не выделено ни одной записи!")
              .withOkButton().open();
    } else {
      E entity = (E) objs.toArray()[0];
      MessageBox.createQuestion().withCaption("Удаление")
              .withMessage("Удалить запись " + (entity instanceof BaseNamedEntity ? "\"" + ((BaseNamedEntity) entity).getName() + "\"" : "с кодом " + entity.getId()) + " ?")
              .withOkButton(() -> internalOnRemoveRecord(entity)).withCancelButton().open();
    }
  }

  private void doEditRecord(Button.ClickEvent event) {
    Set objs = grid.getSelectedItems();
    if (objs.size() == 0) {
      MessageBox.createError().withCaption("Удаление")
              .withMessage("Не выделено ни одной записи!")
              .withOkButton().open();
    } else {
      onEditRecord((E) objs.toArray()[0]);
    }
  }

  private void doReloadTable(Button.ClickEvent event) {
    Set<E> selectedItem = grid.getSelectedItems();
    loadList();
    grid.getSelectionModel().select(selectedItem.toArray());
    editBtn.setEnabled(false);
    removeBtn.setEnabled(false);
  }

  public abstract MetadataGrid buildGrid();

  public void recordSelected(SelectionEvent event) {
  }

  private void doOnSelectRecord(SelectionEvent event) {
    Optional optionalRecord = event.getFirstSelectedItem();
    removeBtn.setEnabled(optionalRecord.isPresent());
    if (selectionMode) {
      selectOkButton.setEnabled(optionalRecord.isPresent());
    }
    if (optionalRecord.isPresent()) {
      Object obj = optionalRecord.get();
      if (obj instanceof SystemRecordDetectable) {
        removeBtn.setEnabled(!((SystemRecordDetectable) obj).isSystem());
      }
    }
    recordSelected(event);
  }

  private void returnSelectedRecordAndClose(E entity) {
    formWindow.close();
    if (selectorHandler != null) {
      selectorHandler.onRecordSelected((BaseNamedEntity) entity);
    }
  }

  @Override
  public final Layout buildContent() {
    GridLayout content = new GridLayout(1, 100);
    content.setSizeFull();
    setCaption(getPageCaption());

    grid = buildGrid();
    grid.setSizeFull();
//    grid.set
    grid.addSelectionListener(event -> doOnSelectRecord(event));
    grid.addItemClickListener(event -> {
      if (event.getMouseEventDetails().isDoubleClick()) {
        if (selectionMode) {
          E entity = (E) event.getItem();
          returnSelectedRecordAndClose(entity);
        } else if (editBtn.isEnabled()) {
          editBtn.click();
        }
      }
    });
    content.addComponent(buildToolbar(), 0, 0, 0, 0);
    content.addComponent(grid, 0, 1, 0, 99);

    return content;
  }

  private Layout buildToolbar() {
    SimpleToolBar toolbar = new SimpleToolBar();
    selectOkButton = toolbar.createAndAddButton("", "toolButtonOk", event -> {
      E entity = (E) grid.getSelectedItems().toArray()[0];
      returnSelectedRecordAndClose(entity);
    });
    selectOkButton.setEnabled(false);
    selectOkButton.setVisible(false);

    closeButton = toolbar.createAndAddButton("", "toolButtonExit", event -> {
      formWindow.close();
    });
    closeButton.setVisible(false);

    reloadBtn = toolbar.createAndAddButton("", "toolButtonReload", event -> doReloadTable(event));
    addBtn = toolbar.createAndAddButton("", "toolButtonNew", event -> onNewRecord(event));
    editBtn = toolbar.createAndAddButton("", "toolButtonEdit", event -> doEditRecord(event));
    editBtn.setEnabled(false);
    removeBtn = toolbar.createAndAddButton("", "toolButtonRemove", event -> askForRemoveRecord(event));
    removeBtn.setEnabled(false);
    addActions(toolbar);
    return toolbar;
  }

  public abstract void loadList();

  @Override
  public void onOpen(ViewChangeListener.ViewChangeEvent event) {
    loadList();
  }

  public MetadataGrid getGrid() {
    return grid;
  }

  //===================================================================================================================
  //===================================================================================================================

  public interface RecordFromListFormSelectedEvent {
    void onRecordSelected(BaseNamedEntity entity);
  }

}
