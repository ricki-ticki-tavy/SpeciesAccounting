package org.ricki.catalog.web.page.styles.forms;

import com.vaadin.ui.GridLayout;
import com.vaadin.ui.Layout;
import org.ricki.catalog.web.abstracts.component.toolbar.SimpleToolBar;
import org.ricki.catalog.web.abstracts.form.element.MetadataForm;
import org.ricki.catalog.web.abstracts.form.element.annotations.FormMetadata;
import org.ricki.catalog.web.abstracts.form.element.annotations.field.area.TextAreaFieldMetadata;
import org.ricki.catalog.web.abstracts.form.element.annotations.field.text.TextFieldMetadata;
import org.ricki.catalog.web.page.styles.entity.UserWebStyle;
import org.ricki.catalog.web.page.styles.service.StyleService;

@FormMetadata(columnCount = 80, rowCount = 10, caption = "Стиль", entityClass = UserWebStyle.class, service = StyleService.class)
@TextFieldMetadata(fieldName = "name", caption = "Название", captionCellWidth = 16, row = 1, column = 3, columnEnd = 76)
@TextFieldMetadata(fieldName = "systemName", caption = "Системное название", captionCellWidth = 16, row = 2, column = 3, columnEnd = 76)
@TextAreaFieldMetadata(fieldName = "styleText", caption = "CSS код", captionCellWidth = 16, row = 3, column = 3, columnEnd = 76, height = 150)
@TextFieldMetadata(fieldName = "comment", caption = "Примечание", captionCellWidth = 16, row = 4, column = 3, columnEnd = 76)
public class StyleEditForm extends MetadataForm<UserWebStyle> {

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
