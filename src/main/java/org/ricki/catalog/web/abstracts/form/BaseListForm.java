package org.ricki.catalog.web.abstracts.form;

import com.vaadin.ui.Button;
import com.vaadin.ui.GridLayout;
import com.vaadin.ui.Layout;
import org.ricki.catalog.web.abstracts.component.grid.MetadataGrid;
import org.ricki.catalog.web.abstracts.component.toolbar.SimpleToolBar;

public abstract class BaseListForm extends BaseForm {
  protected Button addBtn, editBtn, removeBtn;
  protected MetadataGrid grid;

  public void addActions(SimpleToolBar toolbar) {

  }

  public void newRecord(Button.ClickEvent event) {

  }

  public void editRecord(Button.ClickEvent event) {

  }

  public void removeRecord(Button.ClickEvent event) {

  }

  public abstract MetadataGrid buildGrid();

  @Override
  public final Layout buildContent() {
    GridLayout content = new GridLayout(1, 100);
    content.setSizeFull();
    setCaption(getPageCaption());

    grid = buildGrid();
    grid.setSizeFull();
    content.addComponent(buildToolbar(), 0, 0, 0, 0);
    content.addComponent(grid, 0, 1, 0, 99);

    return content;
  }


  private Layout buildToolbar() {
    SimpleToolBar toolbar = new SimpleToolBar();
    addBtn = toolbar.createAndAddButton("", "toolButtonNew", event -> newRecord(event));
    editBtn = toolbar.createAndAddButton("", "toolButtonEdit", event -> editRecord(event));
    editBtn.setEnabled(false);
    removeBtn = toolbar.createAndAddButton("", "toolButtonRemove", event -> removeRecord(event));
    removeBtn.setEnabled(false);
    addActions(toolbar);
    return toolbar;
  }


}
