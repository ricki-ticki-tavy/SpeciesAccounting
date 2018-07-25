package org.ricki.catalog.web.page.actions.forms;

import com.vaadin.ui.GridLayout;
import com.vaadin.ui.Layout;
import org.ricki.catalog.web.abstracts.component.toolbar.SimpleToolBar;
import org.ricki.catalog.web.abstracts.form.element.MetadataForm;
import org.ricki.catalog.web.abstracts.form.element.annotations.FormMetadata;
import org.ricki.catalog.web.abstracts.form.element.annotations.field.text.TextFieldMetadata;
import org.ricki.catalog.web.page.actions.entity.ActionResult;
import org.ricki.catalog.web.page.actions.service.AnActionService;

@FormMetadata(columnCount = 80, rowCount = 10, caption = "Результат выполнения действия", entityClass = ActionResult.class, service = AnActionService.class)
@TextFieldMetadata(fieldName = "name", caption = "Название", captionCellWidth = 16, row = 2, column = 3, columnEnd = 76)
public class ActionResultEditForm extends MetadataForm<ActionResult> {

  @Override
  public void addActions(SimpleToolBar toolbar) {
    saveButton.addClickListener(event -> {
      save();
    });
  }

  @Override
  public Layout buildContent() {
    GridLayout rootContent = (GridLayout) super.buildContent();
    return rootContent;
  }
}
