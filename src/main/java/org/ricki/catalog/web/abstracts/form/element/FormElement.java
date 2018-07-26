package org.ricki.catalog.web.abstracts.form.element;

import com.vaadin.ui.*;
import org.ricki.catalog.entity.abstracts.BaseEntity;
import org.ricki.catalog.entity.abstracts.BaseNamedEntity;
import org.ricki.catalog.system.ReflectionUtils;
import org.ricki.catalog.web.abstracts.form.component.referenceField.ReferenceField;
import org.ricki.catalog.web.abstracts.form.element.annotations.FieldType;

import java.lang.reflect.Field;

/**
 * Элементы формы
 */
public class FormElement {
  Component field;
  Label caption;
  FieldType fieldType;


  String getterName;
  String setterName;

  public void setValue(Object value) {
    switch (fieldType) {
      case TEXT: {
        ((TextField) field).setValue(value.toString());
        break;
      }
      case LABEL: {
        ((Label) field).setValue(value.toString());
        break;
      }
      case MULTI_LINE_TEXT: {
        ((TextArea) field).setValue(value.toString());
        break;
      }
      case CHECKBOX: {
        if (value.getClass().equals(boolean.class) || value.getClass().equals(Boolean.class)) {
          ((CheckBox) field).setValue((Boolean) value);
        } else {
          throw new RuntimeException("Invalid value type (" + value.getClass().getSimpleName() + ") for checkbox");
        }
        break;
      }
      case COMBOBOX: {
        if (value instanceof Enum) {
          ((ComboBox) field).setValue(value);
        } else {
          throw new RuntimeException("Invalid value type (" + value.getClass().getSimpleName() + ") for combobox");
        }
        break;
      }
      case REFERENCE: {
        if (value instanceof BaseNamedEntity) {
          ((ReferenceField) field).setValue((BaseNamedEntity) value);
        } else {
          throw new RuntimeException("Invalid value type (" + value.getClass().getSimpleName() + ") for combobox");
        }
        break;
      }

    }
  }

  public void readValueToEntity(String fieldName, BaseEntity entity) {
    String strValue = null;
    Boolean boolValue = null;
    Object objValue = null;
    switch (fieldType) {
      case TEXT: {
        strValue = ((TextField) field).getValue();
        break;
      }
      case MULTI_LINE_TEXT: {
        strValue = ((TextArea) field).getValue();
        break;
      }
      case CHECKBOX: {
        boolValue = ((CheckBox) field).getValue();
        break;
      }
      case COMBOBOX: {
        objValue = ((ComboBox) field).getValue();
        break;
      }
    }

    Field entityField = ReflectionUtils.getEntityFieldReflection(entity.getClass(), fieldName);

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
}
