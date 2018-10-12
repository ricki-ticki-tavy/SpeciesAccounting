package org.ricki.catalog.web.page.food.form;

import com.vaadin.ui.GridLayout;
import com.vaadin.ui.Layout;
import org.ricki.catalog.web.abstracts.component.toolbar.SimpleToolBar;
import org.ricki.catalog.web.abstracts.form.element.MetadataForm;
import org.ricki.catalog.web.abstracts.form.element.annotations.FormMetadata;
import org.ricki.catalog.web.abstracts.form.element.annotations.field.bool.BooleanFieldMetadata;
import org.ricki.catalog.web.abstracts.form.element.annotations.field.numeric.NumericFieldMetadata;
import org.ricki.catalog.web.abstracts.form.element.annotations.field.text.TextFieldMetadata;
import org.ricki.catalog.web.page.food.entity.Food;
import org.ricki.catalog.web.page.food.service.FoodService;

@FormMetadata(columnCount = 80, rowCount = 10, caption = "Корм", service = FoodService.class)
//@BooleanFieldMetadata(fieldName = "system", caption = "Системное", captionCellWidth = 16, row = 1, column = 3)
@BooleanFieldMetadata(fieldName = "active", caption = "Запись активна", captionCellWidth = 16, row = 1, column = 3)
@TextFieldMetadata(fieldName = "name", caption = "Название", captionCellWidth = 16, row = 2, column = 3, columnEnd = 76)

@TextFieldMetadata(fieldName = "comment", caption = "Примечание", captionCellWidth = 16, row = 3, column = 3, columnEnd = 76)
@BooleanFieldMetadata(fieldName = "forSale", caption = "Продается", captionCellWidth = 16, row = 4, column = 3)
@NumericFieldMetadata(fieldName = "requiredPrice", caption = "Цена", captionCellWidth = 16, row = 5, column = 3, columnEnd = 76, format = "0.##")
@BooleanFieldMetadata(fieldName = "published", caption = "Опубликовано в каталоге", captionCellWidth = 16, row = 6, column = 3)


//@ComboBoxFieldMetadata(fieldName = "actionRecipient", caption = "Применимо к", enumList = AnAction.ActionRecipient.class, captionCellWidth = 16, row = 3, column = 3, columnEnd = 76)
//@TableReferenceFieldMetadata(fieldName = "availableResults", caption = "Возможные результаты"
//        , height = 250, captionCellWidth = 16, row = 4, column = 3, columnEnd = 76
//        , entityClass = ActionResult.class, entityListFormClass = ActionResultListForm.class, gridMetadata = @GridMetadata(gridUniqueId = "Action_actionResults"
//        , columns = {@ColumnInfo(fieldName = "name", columnCaption = "Название", width = 500)
//        , @ColumnInfo(fieldName = "style", columnCaption = "Стиль отображения", width = 180)}))
//@ReferenceFieldMetadata(fieldName = "planedStyle", caption = "Стиль запланир. действия", entityClass = UserWebStyle.class, entitySelectorFormClass = StyleListForm.class
//            , row = 5, column = 3, columnEnd = 76, captionCellWidth = 16)
public class FoodEditForm extends MetadataForm<Food> {

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
