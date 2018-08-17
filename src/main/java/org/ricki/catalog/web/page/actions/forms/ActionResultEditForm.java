package org.ricki.catalog.web.page.actions.forms;

import com.vaadin.ui.GridLayout;
import com.vaadin.ui.Layout;
import org.ricki.catalog.web.abstracts.component.toolbar.SimpleToolBar;
import org.ricki.catalog.web.abstracts.form.element.MetadataForm;
import org.ricki.catalog.web.abstracts.form.element.annotations.FormMetadata;
import org.ricki.catalog.web.abstracts.form.element.annotations.field.reference.ReferenceFieldMetadata;
import org.ricki.catalog.web.abstracts.form.element.annotations.field.text.TextFieldMetadata;
import org.ricki.catalog.web.page.actions.entity.ActionResult;
import org.ricki.catalog.web.page.actions.service.ActionResultService;
import org.ricki.catalog.web.page.styles.entity.UserWebStyle;
import org.ricki.catalog.web.page.styles.forms.StyleListForm;

@FormMetadata(columnCount = 80, rowCount = 10, caption = "Результат выполнения действия", service = ActionResultService.class)
@TextFieldMetadata(fieldName = "name", caption = "Название", captionCellWidth = 16, row = 2, column = 3, columnEnd = 76)
@ReferenceFieldMetadata(fieldName = "style", caption = "Стиль отображения", captionCellWidth = 16, row = 3, column = 3, columnEnd = 76
        , entityClass = UserWebStyle.class, entitySelectorFormClass = StyleListForm.class)
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
