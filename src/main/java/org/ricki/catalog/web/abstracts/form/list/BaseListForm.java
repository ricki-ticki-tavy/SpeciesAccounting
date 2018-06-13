package org.ricki.catalog.web.abstracts.form.list;

import com.vaadin.event.selection.SelectionEvent;
import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.ui.Button;
import com.vaadin.ui.GridLayout;
import com.vaadin.ui.Layout;
import de.steinwedel.messagebox.MessageBox;
import org.ricki.catalog.entity.abstracts.BaseEntity;
import org.ricki.catalog.entity.abstracts.BaseNamedEntity;
import org.ricki.catalog.entity.abstracts.SystemRecordDetectable;
import org.ricki.catalog.web.abstracts.component.grid.MetadataGrid;
import org.ricki.catalog.web.abstracts.component.toolbar.SimpleToolBar;

import java.util.Optional;
import java.util.Set;

public abstract class BaseListForm<E extends BaseEntity> extends BaseForm {
  protected Button addBtn, editBtn, removeBtn, reloadBtn;
  protected MetadataGrid grid;

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
              .withOkButton(() -> onRemoveRecord(entity)).withCancelButton().open();
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
    loadList();
    editBtn.setEnabled(false);
    removeBtn.setEnabled(false);
  }

  public abstract MetadataGrid buildGrid();

  public void recordSelected(SelectionEvent event) {
  }

  private void doOnSelectRecord(SelectionEvent event) {
    Optional optionalRecord = event.getFirstSelectedItem();
    removeBtn.setEnabled(optionalRecord.isPresent());
    if (optionalRecord.isPresent()) {
      Object obj = optionalRecord.get();
      if (obj instanceof SystemRecordDetectable) {
        removeBtn.setEnabled(!((SystemRecordDetectable) obj).isSystem());
      }
    }
    recordSelected(event);
  }

  @Override
  public final Layout buildContent() {
    GridLayout content = new GridLayout(1, 100);
    content.setSizeFull();
    setCaption(getPageCaption());

    grid = buildGrid();
    grid.setSizeFull();
    grid.addSelectionListener(event -> doOnSelectRecord(event));
//    grid.addItemClickListener(event -> recordSelected(event));
    content.addComponent(buildToolbar(), 0, 0, 0, 0);
    content.addComponent(grid, 0, 1, 0, 99);

    return content;
  }

  private Layout buildToolbar() {
    SimpleToolBar toolbar = new SimpleToolBar();
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

}
