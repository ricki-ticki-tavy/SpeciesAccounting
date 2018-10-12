package org.ricki.catalog.web.page.specie.form;

import com.vaadin.ui.GridLayout;
import com.vaadin.ui.Layout;
import org.ricki.catalog.web.abstracts.component.toolbar.SimpleToolBar;
import org.ricki.catalog.web.abstracts.form.element.MetadataForm;
import org.ricki.catalog.web.abstracts.form.element.annotations.FormMetadata;
import org.ricki.catalog.web.abstracts.form.element.annotations.field.text.TextFieldMetadata;
import org.ricki.catalog.web.page.specie.entity.Specie;
import org.ricki.catalog.web.page.specie.service.SpecieService;

@FormMetadata(columnCount = 80, rowCount = 10, caption = "Особь", service = SpecieService.class)
@TextFieldMetadata(fieldName = "name", caption = "Название", captionCellWidth = 16, row = 1, column = 3, columnEnd = 76)
//@ComboBoxFieldMetadata(fieldName = "actionRecipient", caption = "Применимо к", enumList = Specie.ActionRecipient.class, captionCellWidth = 16, row = 3, column = 3, columnEnd = 76)
//@TableReferenceFieldMetadata(fieldName = "availableResults", caption = "Возможные результаты"
//        , height = 250, captionCellWidth = 16, row = 4, column = 3, columnEnd = 76
//        , entityClass = ActionResult.class, entityListFormClass = ActionResultListForm.class, gridMetadata = @GridMetadata(gridUniqueId = "Action_actionResults"
//        , columns = {@ColumnInfo(fieldName = "name", columnCaption = "Название", width = 500)
//        , @ColumnInfo(fieldName = "style", columnCaption = "Стиль отображения", width = 180)}))
//@ReferenceFieldMetadata(fieldName = "planedStyle", caption = "Стиль запланир. действия", entityClass = UserWebStyle.class, entitySelectorFormClass = StyleListForm.class
//        , row = 5, column = 3, columnEnd = 76, captionCellWidth = 16)
public class SpecieEditForm extends MetadataForm<Specie> {

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
