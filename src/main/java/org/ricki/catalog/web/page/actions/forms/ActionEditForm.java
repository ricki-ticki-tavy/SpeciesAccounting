package org.ricki.catalog.web.page.actions.forms;

import com.vaadin.ui.GridLayout;
import com.vaadin.ui.Layout;
import org.ricki.catalog.web.abstracts.component.toolbar.SimpleToolBar;
import org.ricki.catalog.web.abstracts.form.element.MetadataForm;
import org.ricki.catalog.web.abstracts.form.element.annotations.FormMetadata;
import org.ricki.catalog.web.abstracts.form.element.annotations.field.bool.BooleanFieldMetadata;
import org.ricki.catalog.web.abstracts.form.element.annotations.field.combobox.ComboBoxFieldMetadata;
import org.ricki.catalog.web.abstracts.form.element.annotations.field.text.TextFieldMetadata;
import org.ricki.catalog.web.page.actions.entity.AnAction;
import org.ricki.catalog.web.page.actions.service.AnActionService;

@FormMetadata(columnCount = 80, rowCount = 10, caption = "Действие", service = AnActionService.class)
@BooleanFieldMetadata(fieldName = "system", caption = "Системное", captionCellWidth = 16, row = 1, column = 3)
@TextFieldMetadata(fieldName = "name", caption = "Название", captionCellWidth = 16, row = 2, column = 3, columnEnd = 76)
@ComboBoxFieldMetadata(fieldName = "actionRecipient", caption = "Применимо к", enumList = AnAction.ActionRecipient.class, captionCellWidth = 16, row = 3, column = 3, columnEnd = 76)
//@ChildTableFieldMetadata(fieldName = "availableResults", caption = "Возможные результаты", captionCellWidth = 16, row = 3, column = 3
//        , columnEnd = 76, height = 150, childClass = ActionResult.class, gridMetadata = @GridMetadata(gridUniqueId = "actionEditForm-availResults", columns = {
//        @ColumnInfo(fieldName =)}))
//@TextFieldMetadata(fieldName = "systemName", caption = "Системное название", captionCellWidth = 16, row = 2, column = 3, columnEnd = 76)
//@TextAreaFieldMetadata(fieldName = "styleText", caption = "CSS код", captionCellWidth = 16, row = 3, column = 3, columnEnd = 76, height = 150)
//@TextFieldMetadata(fieldName = "comment", caption = "Примечание", captionCellWidth = 16, row = 4, column = 3, columnEnd = 76)
public class ActionEditForm extends MetadataForm<AnAction> {

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
