package org.ricki.catalog.web.abstracts.form.component.referenceField;

import com.vaadin.ui.Button;
import com.vaadin.ui.CssLayout;
import com.vaadin.ui.TextField;
import com.vaadin.ui.themes.ValoTheme;
import org.ricki.catalog.entity.abstracts.BaseNamedEntity;
import org.ricki.catalog.web.abstracts.form.list.BaseListForm;
import org.springframework.web.context.support.SpringBeanAutowiringSupport;

public class ReferenceField<E extends BaseNamedEntity> extends CssLayout {

  Button selectButton;
  TextField field;
  Class<? extends BaseListForm> entityListFormClass;
  BaseListForm entityListForm = null;
  E entityValue = null;

  public void setValue(E value) {
    field.setReadOnly(false);
    field.setValue(value.getName());
    this.entityValue = value;
    field.setReadOnly(true);
  }

  public E getValue() {
    return entityValue;
  }

  public ReferenceField(Class<? extends BaseListForm> entityListFormClass) {
    this.entityListFormClass = entityListFormClass;
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

    selectButton.addClickListener(event -> {
      try {
        if (entityListForm == null) {
          entityListForm = entityListFormClass.newInstance();
          SpringBeanAutowiringSupport.processInjectionBasedOnCurrentContext(entityListForm);
        }
        entityListForm.showForSelect(entity -> {
          setValue((E) entity);
        });
      } catch (IllegalAccessException | InstantiationException e) {
        throw new RuntimeException(e);
      }
    });
  }


}
