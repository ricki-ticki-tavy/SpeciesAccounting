package org.ricki.catalog.web.abstracts.form.component.referenceField;

import com.vaadin.ui.Button;
import com.vaadin.ui.CssLayout;
import com.vaadin.ui.TextField;
import com.vaadin.ui.themes.ValoTheme;
import org.ricki.catalog.entity.abstracts.BaseNamedEntity;

public class ReferenceField<E extends BaseNamedEntity> extends CssLayout {

  Button selectButton;
  TextField field;

  public void setValue(E value) {
    field.setReadOnly(false);
    field.setValue(value.getName());
    field.setReadOnly(true);
  }

  public E getValue() {
    return null;
  }

  public ReferenceField() {
    setStyleName(ValoTheme.LAYOUT_COMPONENT_GROUP);
//    setWidth(100, Unit.PERCENTAGE);
    setCaption(null);

    field = new TextField();
    field.setReadOnly(true);
    field.setWidth(100, Unit.PERCENTAGE);
    field.setHeight(100, Unit.PERCENTAGE);

    addComponent(field);

    selectButton = new Button("...");
    selectButton.setHeight(100, Unit.PERCENTAGE);
    selectButton.setWidth(32, Unit.PIXELS);

    addComponent(selectButton);
  }

}
