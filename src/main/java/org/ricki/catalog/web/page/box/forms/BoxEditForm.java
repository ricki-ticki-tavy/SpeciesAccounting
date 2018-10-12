package org.ricki.catalog.web.page.box.forms;

import com.vaadin.ui.GridLayout;
import com.vaadin.ui.Layout;
import org.ricki.catalog.web.abstracts.component.toolbar.SimpleToolBar;
import org.ricki.catalog.web.abstracts.form.element.MetadataForm;
import org.ricki.catalog.web.abstracts.form.element.annotations.FormMetadata;
import org.ricki.catalog.web.abstracts.form.element.annotations.field.bool.BooleanFieldMetadata;
import org.ricki.catalog.web.abstracts.form.element.annotations.field.numeric.NumericFieldMetadata;
import org.ricki.catalog.web.abstracts.form.element.annotations.field.text.TextFieldMetadata;
import org.ricki.catalog.web.abstracts.form.element.annotations.field.textArea.TextAreaFieldMetadata;
import org.ricki.catalog.web.page.box.entity.Box;
import org.ricki.catalog.web.page.box.service.BoxService;

@FormMetadata(columnCount = 80, rowCount = 10, caption = "Террариум", service = BoxService.class)
@BooleanFieldMetadata(fieldName = "active", caption = "В наличии и в норме", captionCellWidth = 16, row = 1, column = 3)
@TextFieldMetadata(fieldName = "name", caption = "Название", captionCellWidth = 16, row = 2, column = 3, columnEnd = 76)
@TextFieldMetadata(fieldName = "info", caption = "Параметры", captionCellWidth = 16, row = 3, column = 3, columnEnd = 76)
@BooleanFieldMetadata(fieldName = "forSale", caption = "В продаже", captionCellWidth = 16, row = 4, column = 3)
@NumericFieldMetadata(fieldName = "requiredPrice", caption = "Цена", format = "0.##", captionCellWidth = 16, row = 5, column = 3, columnEnd = 76)
@TextAreaFieldMetadata(fieldName = "comment", caption = "Примечание", captionCellWidth = 16, row = 6, column = 3, columnEnd = 76, height = 150)
//@TableReferenceFieldMetadata(fieldName = "availableResults", caption = "Возможные результаты"
//        , height = 250, captionCellWidth = 16, row = 4, column = 3, columnEnd = 76
//        , entityClass = ActionResult.class, entityListFormClass = ActionResultListForm.class, gridMetadata = @GridMetadata(gridUniqueId = "Action_actionResults"
//        , columns = {@ColumnInfo(fieldName = "name", columnCaption = "Название", width = 500)
//        , @ColumnInfo(fieldName = "style", columnCaption = "Стиль отображения", width = 180)}))
public class BoxEditForm extends MetadataForm<Box> {

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
