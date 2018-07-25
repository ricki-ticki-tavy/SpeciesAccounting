package org.ricki.catalog.web.abstracts.form.element;

import com.vaadin.ui.*;
import org.ricki.catalog.entity.abstracts.BaseEntity;
import org.ricki.catalog.entity.abstracts.BaseNamedEntity;
import org.ricki.catalog.service.base.BaseService;
import org.ricki.catalog.web.abstracts.form.common.Styles;
import org.ricki.catalog.web.abstracts.form.component.referenceField.ReferenceField;
import org.ricki.catalog.web.abstracts.form.element.annotations.FieldType;
import org.ricki.catalog.web.abstracts.form.element.annotations.FormMetadata;
import org.ricki.catalog.web.abstracts.form.element.annotations.field.area.TextAreaFieldMetadata;
import org.ricki.catalog.web.abstracts.form.element.annotations.field.area.TextAreaFieldsMetadata;
import org.ricki.catalog.web.abstracts.form.element.annotations.field.bool.BooleanFieldMetadata;
import org.ricki.catalog.web.abstracts.form.element.annotations.field.bool.BooleanFieldsMetadata;
import org.ricki.catalog.web.abstracts.form.element.annotations.field.combobox.ComboBoxFieldMetadata;
import org.ricki.catalog.web.abstracts.form.element.annotations.field.combobox.ComboBoxFieldsMetadata;
import org.ricki.catalog.web.abstracts.form.element.annotations.field.reference.ReferenceFieldMetadata;
import org.ricki.catalog.web.abstracts.form.element.annotations.field.reference.ReferenceFieldsMetadata;
import org.ricki.catalog.web.abstracts.form.element.annotations.field.text.TextFieldMetadata;
import org.ricki.catalog.web.abstracts.form.element.annotations.field.text.TextFieldsMetadata;
import org.ricki.catalog.web.abstracts.form.list.BaseListForm;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.util.StringUtils;
import org.springframework.web.context.support.SpringBeanAutowiringSupport;

import javax.inject.Inject;
import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.Map;

public abstract class MetadataForm<E extends BaseEntity> extends BaseEditFormWithToolBar {

  private Map<String, FormElement> formElementsMap;

  private GridLayout formLayout;

  protected E entity;

  protected BaseListForm parentListForm;

  protected BaseService service;

  @Inject
  BeanFactory beanFactory;

  private int addCaption(FormElement formElement, String caption, int column, int row, int cellWidth) {
    if (!StringUtils.isEmpty(caption)) {
      HorizontalLayout captionLayout = new HorizontalLayout();
      captionLayout.setSizeFull();

      formElement.caption = new Label(caption);
      formElement.caption.addStyleName(Styles.FORM_FIELD_CAPTION_ENABLEDMODE_STYLE);
      captionLayout.addComponent(formElement.caption);

      formLayout.addComponent(captionLayout, column, row, column + cellWidth - 1, row);
      return column + cellWidth;
    } else {
      return column;
    }

  }

  private void parseFieldAnnotations(Object[] annotations) {
    for (Object abstractFieldMetadata : annotations) {
      FormElement newElement = new FormElement();
      String fieldName = null;

      if (abstractFieldMetadata instanceof TextFieldMetadata) {
        newElement.fieldType = FieldType.TEXT;

        TextFieldMetadata fieldMetadata = (TextFieldMetadata) abstractFieldMetadata;
        int columnForField = addCaption(newElement, fieldMetadata.caption(), fieldMetadata.column(), fieldMetadata.row(), fieldMetadata.captionCellWidth());

        fieldName = fieldMetadata.fieldName();
        newElement.field = new TextField();
        newElement.field.setWidth(100, Unit.PERCENTAGE);

        formLayout.addComponent(newElement.field, columnForField, fieldMetadata.row(),
                fieldMetadata.columnEnd(), fieldMetadata.row());
      } else if (abstractFieldMetadata instanceof TextAreaFieldMetadata) {
        newElement.fieldType = FieldType.MULTI_LINE_TEXT;

        TextAreaFieldMetadata fieldMetadata = (TextAreaFieldMetadata) abstractFieldMetadata;
        int columnForField = addCaption(newElement, fieldMetadata.caption(), fieldMetadata.column(), fieldMetadata.row(), fieldMetadata.captionCellWidth());

        newElement.field = new TextArea();
        newElement.field.setSizeFull();
        newElement.field.setHeight(fieldMetadata.height(), Unit.PIXELS);
        fieldName = fieldMetadata.fieldName();

        formLayout.addComponent(newElement.field, columnForField, fieldMetadata.row(),
                fieldMetadata.columnEnd(), fieldMetadata.row());

      } else if (abstractFieldMetadata instanceof BooleanFieldMetadata) {
        newElement.fieldType = FieldType.CHECKBOX;

        BooleanFieldMetadata fieldMetadata = (BooleanFieldMetadata) abstractFieldMetadata;
        int columnForField = addCaption(newElement, fieldMetadata.caption(), fieldMetadata.column(), fieldMetadata.row(), fieldMetadata.captionCellWidth());

        fieldName = fieldMetadata.fieldName();
        newElement.field = new CheckBox();
        newElement.field.setWidth(100, Unit.PERCENTAGE);

        formLayout.addComponent(newElement.field, columnForField, fieldMetadata.row(),
                columnForField, fieldMetadata.row());
      } else if (abstractFieldMetadata instanceof ComboBoxFieldMetadata) {
        newElement.fieldType = FieldType.COMBOBOX;

        ComboBoxFieldMetadata fieldMetadata = (ComboBoxFieldMetadata) abstractFieldMetadata;
        int columnForField = addCaption(newElement, fieldMetadata.caption(), fieldMetadata.column(), fieldMetadata.row(), fieldMetadata.captionCellWidth());

        fieldName = fieldMetadata.fieldName();
        ComboBox cb = new ComboBox();

        cb.setItems(fieldMetadata.enumList().getEnumConstants());
        newElement.field = cb;
        newElement.field.setWidth(100, Unit.PERCENTAGE);

        formLayout.addComponent(newElement.field, columnForField, fieldMetadata.row(),
                fieldMetadata.columnEnd(), fieldMetadata.row());
      } else if (abstractFieldMetadata instanceof ReferenceFieldMetadata) {
        newElement.fieldType = FieldType.REFERENCE;

        ReferenceFieldMetadata fieldMetadata = (ReferenceFieldMetadata) abstractFieldMetadata;
        int columnForField = addCaption(newElement, fieldMetadata.caption(), fieldMetadata.column(), fieldMetadata.row(), fieldMetadata.captionCellWidth());

        fieldName = fieldMetadata.fieldName();
        ReferenceField refField = new ReferenceField();
        newElement.field = refField;
        newElement.field.setWidth(100, Unit.PERCENTAGE);

        formLayout.addComponent(newElement.field, columnForField, fieldMetadata.row(),
                fieldMetadata.columnEnd(), fieldMetadata.row());
      }

      if (!StringUtils.isEmpty(fieldName)) {
        newElement.field.setId(fieldName);
        formElementsMap.put(fieldName, newElement);
      }
    }
  }

  private <R extends Annotation, F extends Annotation> void processFieldAnnotationsByType(Class<R> repeatableAnnotationClass
          , Class<F> fieldAnnotationClass) {
    R fieldsList = this.getClass().getDeclaredAnnotation(repeatableAnnotationClass);
    if (fieldsList != null) {
      try {
        parseFieldAnnotations((Annotation[]) fieldsList.getClass().getDeclaredMethod("value").invoke(fieldsList));
      } catch (NoSuchMethodException | InvocationTargetException | IllegalAccessException nsme) {
        throw new RuntimeException(nsme);
      }
    } else {
      F field = this.getClass().getDeclaredAnnotation(fieldAnnotationClass);
      if (field != null) {
        parseFieldAnnotations(new Annotation[]{field});
      }
    }
  }

  private void createAllFields() {
    processFieldAnnotationsByType(BooleanFieldsMetadata.class, BooleanFieldMetadata.class);
    processFieldAnnotationsByType(TextFieldsMetadata.class, TextFieldMetadata.class);
    processFieldAnnotationsByType(TextAreaFieldsMetadata.class, TextAreaFieldMetadata.class);
    processFieldAnnotationsByType(ComboBoxFieldsMetadata.class, ComboBoxFieldMetadata.class);
    processFieldAnnotationsByType(ReferenceFieldsMetadata.class, ReferenceFieldMetadata.class);
  }

  @Override
  public Layout buildContent() {
    FormMetadata formMetadata = this.getClass().getDeclaredAnnotation(FormMetadata.class);

    formLayout = new GridLayout();
    formLayout.setSizeFull();
    formLayout.setSpacing(true);

    formElementsMap = new HashMap<>();

    if (formMetadata != null) {
      formLayout.setColumns(formMetadata.columnCount());
      formLayout.setRows(formMetadata.rowCount() + 1);

      setWindowCaption(formMetadata.caption());

      SpringBeanAutowiringSupport.processInjectionBasedOnCurrentContext(this);
      String serviceBeanName = formMetadata.service().getSimpleName();
      serviceBeanName = serviceBeanName.substring(0, 1).toLowerCase() + serviceBeanName.substring(1);
      service = beanFactory.getBean(serviceBeanName, BaseService.class);

    }

    createAllFields();

    HorizontalLayout spacer = new HorizontalLayout();
    spacer.setWidth(100, Unit.PERCENTAGE);
    spacer.setHeight(1000, Unit.PIXELS);
    formLayout.addComponent(spacer, 0, formMetadata.rowCount() - 1, formMetadata.columnCount() - 1, formMetadata.rowCount() - 1);
    formLayout.setRowExpandRatio(formMetadata.rowCount() + 1, 1000);

    return formLayout;
  }

  public E load(long id) {
    entity = (E) service.get(id);
    return entity;
  }

  private void setFieldValue(FormElement formElement, Object value) {
    switch (formElement.fieldType) {
      case TEXT: {
        ((TextField) formElement.field).setValue(value.toString());
        break;
      }
      case LABEL: {
        ((Label) formElement.field).setValue(value.toString());
        break;
      }
      case MULTI_LINE_TEXT: {
        ((TextArea) formElement.field).setValue(value.toString());
        break;
      }
      case CHECKBOX: {
        if (value.getClass().equals(boolean.class) || value.getClass().equals(Boolean.class)) {
          ((CheckBox) formElement.field).setValue((Boolean) value);
        } else {
          throw new RuntimeException("Invalid value type (" + value.getClass().getSimpleName() + ") for checkbox");
        }
        break;
      }
      case COMBOBOX: {
        if (value instanceof Enum) {
          ((ComboBox) formElement.field).setValue(value);
        } else {
          throw new RuntimeException("Invalid value type (" + value.getClass().getSimpleName() + ") for combobox");
        }
        break;
      }
      case REFERENCE: {
        if (value instanceof BaseNamedEntity) {
          ((ReferenceField) formElement.field).setValue((BaseNamedEntity) value);
        } else {
          throw new RuntimeException("Invalid value type (" + value.getClass().getSimpleName() + ") for combobox");
        }
        break;
      }

    }

  }

  private void setEntityFieldValue(FormElement formElement, String fieldName) {
    String strValue = null;
    Boolean boolValue = null;
    Object objValue = null;
    switch (formElement.fieldType) {
      case TEXT: {
        strValue = ((TextField) formElement.field).getValue();
        break;
      }
      case MULTI_LINE_TEXT: {
        strValue = ((TextArea) formElement.field).getValue();
        break;
      }
      case CHECKBOX: {
        boolValue = ((CheckBox) formElement.field).getValue();
        break;
      }
      case COMBOBOX: {
        objValue = ((ComboBox) formElement.field).getValue();
        break;
      }
    }

    Field entityField = getEntityFieldReflection(entity.getClass(), fieldName);

    try {
      Class<?> entityFieldType = entityField.getType();
      if (entityFieldType == String.class) {
        entityField.set(entity, strValue != null ? strValue : boolValue.toString());
      } else if (entityFieldType == Integer.class) {
        entityField.setInt(entity, Integer.parseInt(strValue));
      } else if (entityFieldType == Long.class) {
        entityField.setLong(entity, Long.parseLong(strValue));
      } else if (entityFieldType == Boolean.class) {
        entityField.setBoolean(entity, Boolean.parseBoolean(strValue));
      } else if (entityFieldType == Double.class) {
        entityField.setDouble(entity, Double.parseDouble(strValue));
      } else if (entityFieldType == Float.class) {
        entityField.setFloat(entity, Float.parseFloat(strValue));
      } else if (entityFieldType.getSuperclass() == Enum.class) {
        entityField.set(entity, objValue);
      }
    } catch (IllegalAccessException iae) {
      throw new RuntimeException(iae);
    }
  }

  private Field getEntityFieldReflection(Class<?> clazz, String fieldName) {
    while (clazz != null) {
      try {
        Field field = clazz.getDeclaredField(fieldName);
        field.setAccessible(true);
        return field;
      } catch (NoSuchFieldException rex) {
        clazz = clazz.getSuperclass();
      }
    }
    throw new RuntimeException("Field \"" + fieldName + " not found");
  }

  public void fillForm() {
    fillForm(null);
  }

  public void fillForm(E entity_) {
    Field field;

    if (entity_ != null) {
      this.entity = entity_;
    }

    for (String fieldName : formElementsMap.keySet()) {
      FormElement formElement = formElementsMap.get(fieldName);
      field = getEntityFieldReflection(entity.getClass(), fieldName);

      try {
        setFieldValue(formElement, field.get(entity));
      } catch (IllegalAccessException rex) {
        throw new RuntimeException(rex);
      }
    }
  }

  public final E save() {
    boolean isNew = false;
    if (entity == null) {
      entity = (E) service.create();
      isNew = true;
    }
    for (String fieldName : formElementsMap.keySet()) {
      FormElement formElement = formElementsMap.get(fieldName);

      setEntityFieldValue(formElement, fieldName);
    }

    E newEntity = (E) service.save(entity);

    if (parentListForm != null) {
      if (isNew) {
        parentListForm.onRecordAdded(newEntity);
      } else {
        parentListForm.onRecordUpdated(newEntity);
      }

    }

    return (E) service.save(entity);

  }

  public void setParentListForm(BaseListForm parentListForm) {
    this.parentListForm = parentListForm;
  }
}
