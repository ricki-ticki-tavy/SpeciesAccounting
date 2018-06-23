package org.ricki.catalog.web.abstracts.form.element;

import com.vaadin.ui.Component;
import com.vaadin.ui.Label;
import org.ricki.catalog.web.abstracts.form.element.annotations.FieldType;

/**
 * Элементы формы
 */
public class FormElement {
  Component field;
  Label caption;
  FieldType fieldType;


  String getterName;
  String setterName;
}
