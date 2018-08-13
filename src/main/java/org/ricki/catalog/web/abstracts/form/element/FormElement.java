package org.ricki.catalog.web.abstracts.form.element;

import com.vaadin.ui.*;
import org.ricki.catalog.entity.abstracts.BaseEntity;
import org.ricki.catalog.entity.abstracts.BaseNamedEntity;
import org.ricki.catalog.system.ReflectionUtils;
import org.ricki.catalog.web.abstracts.component.grid.TableReferenceField;
import org.ricki.catalog.web.abstracts.form.component.referenceField.ReferenceField;
import org.ricki.catalog.web.abstracts.form.element.annotations.FieldType;

import java.lang.reflect.Field;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

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
          throw new RuntimeException("Invalid value type (" + value.getClass().getSimpleName() + ") for ReferenceField");
        }
        break;
      }
      case COLLECTION: {
        if (value instanceof Collection) {
          ((TableReferenceField) field).setValue((Collection) value);
        } else {
          throw new RuntimeException("Invalid value type (" + value.getClass().getSimpleName() + ") for tableReferenceField");
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
      case REFERENCE: {
        objValue = ((ReferenceField<BaseNamedEntity>) field).getValue();
        break;
      }
      case COLLECTION: {
        objValue = ((TableReferenceField<BaseNamedEntity>) field).getValue();
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
      } else if ((entityFieldType == Long.class) || (entityFieldType == long.class)) {
        entityField.setLong(entity, Long.parseLong(strValue));
      } else if ((entityFieldType == Boolean.class) || (entityFieldType == boolean.class)) {
        entityField.setBoolean(entity, boolValue);
      } else if (entityFieldType == Double.class) {
        entityField.setDouble(entity, Double.parseDouble(strValue));
      } else if (entityFieldType == Float.class) {
        entityField.setFloat(entity, Float.parseFloat(strValue));
      } else if (entityFieldType.getSuperclass() == Enum.class) {
        entityField.set(entity, objValue);
      } else if (entityFieldType == Set.class) {
        Set value;
        if (objValue instanceof Set) {
          value = (Set) objValue;
        } else if (objValue instanceof List) {
          value = new HashSet((List) objValue);
        } else {
          throw new RuntimeException("invalid collection for " + fieldName);
        }
        entityField.set(entity, value);
      } else if (entity instanceof BaseNamedEntity) {
        entityField.set(entity, objValue);
      }
    } catch (IllegalAccessException iae) {
      throw new RuntimeException(iae);
    }

  }
}
