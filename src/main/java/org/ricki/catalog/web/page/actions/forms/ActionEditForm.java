package org.ricki.catalog.web.page.actions.forms;

import com.vaadin.ui.GridLayout;
import com.vaadin.ui.Layout;
import org.ricki.catalog.web.abstracts.component.toolbar.SimpleToolBar;
import org.ricki.catalog.web.abstracts.form.element.MetadataForm;
import org.ricki.catalog.web.abstracts.form.element.annotations.FormMetadata;
import org.ricki.catalog.web.abstracts.form.element.annotations.field.bool.BooleanFieldMetadata;
import org.ricki.catalog.web.abstracts.form.element.annotations.field.combobox.ComboBoxFieldMetadata;
import org.ricki.catalog.web.abstracts.form.element.annotations.field.grid.ColumnInfo;
import org.ricki.catalog.web.abstracts.form.element.annotations.field.grid.GridMetadata;
import org.ricki.catalog.web.abstracts.form.element.annotations.field.reference.ReferenceFieldMetadata;
import org.ricki.catalog.web.abstracts.form.element.annotations.field.tableReferencs.TableReferenceFieldMetadata;
import org.ricki.catalog.web.abstracts.form.element.annotations.field.text.TextFieldMetadata;
import org.ricki.catalog.web.page.actions.entity.ActionResult;
import org.ricki.catalog.web.page.actions.entity.AnAction;
import org.ricki.catalog.web.page.actions.service.AnActionService;
import org.ricki.catalog.web.page.styles.entity.UserWebStyle;
import org.ricki.catalog.web.page.styles.forms.StyleListForm;

@FormMetadata(columnCount = 80, rowCount = 10, caption = "Действие", service = AnActionService.class)
@BooleanFieldMetadata(fieldName = "system", caption = "Системное", captionCellWidth = 16, row = 1, column = 3)
@TextFieldMetadata(fieldName = "name", caption = "Название", captionCellWidth = 16, row = 2, column = 3, columnEnd = 76)
@ComboBoxFieldMetadata(fieldName = "actionRecipient", caption = "Применимо к", enumList = AnAction.ActionRecipient.class, captionCellWidth = 16, row = 3, column = 3, columnEnd = 76)
@TableReferenceFieldMetadata(fieldName = "availableResults", caption = "Возможные результаты"
        , height = 250, captionCellWidth = 16, row = 4, column = 3, columnEnd = 76
        , entityClass = ActionResult.class, entityListFormClass = ActionResultListForm.class, gridMetadata = @GridMetadata(gridUniqueId = "Action_actionResults"
        , columns = {@ColumnInfo(fieldName = "name", columnCaption = "Название", width = 500)
        , @ColumnInfo(fieldName = "style", columnCaption = "Стиль отображения", width = 180)}))
@ReferenceFieldMetadata(fieldName = "planedStyle", caption = "Стиль запланир. действия", entityClass = UserWebStyle.class, entitySelectorFormClass = StyleListForm.class
        , row = 5, column = 3, columnEnd = 76, captionCellWidth = 16)
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
