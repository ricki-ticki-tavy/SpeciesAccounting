package org.ricki.catalog.web.abstracts.form.element;

import com.vaadin.server.Sizeable;
import com.vaadin.ui.*;
import org.ricki.catalog.web.abstracts.form.common.Styles;
import org.ricki.catalog.web.abstracts.form.component.referenceField.ReferenceField;
import org.ricki.catalog.web.abstracts.form.element.annotations.FieldType;
import org.ricki.catalog.web.abstracts.form.element.annotations.field.area.TextAreaFieldMetadata;
import org.ricki.catalog.web.abstracts.form.element.annotations.field.bool.BooleanFieldMetadata;
import org.ricki.catalog.web.abstracts.form.element.annotations.field.combobox.ComboBoxFieldMetadata;
import org.ricki.catalog.web.abstracts.form.element.annotations.field.reference.ReferenceFieldMetadata;
import org.ricki.catalog.web.abstracts.form.element.annotations.field.text.TextFieldMetadata;
import org.springframework.util.StringUtils;

import java.lang.annotation.Annotation;
import java.util.Map;

public class MetaFieldFactory {

  private GridLayout formLayout;
  private Map<String, FormElement> formElementsMap;

  public FormElement createMetaFieldFromMetaDate(Annotation abstractFieldMetadata) {
    FormElement newElement;
    if (abstractFieldMetadata instanceof TextFieldMetadata) {
      newElement = createTextMetaField((TextFieldMetadata) abstractFieldMetadata);
    } else if (abstractFieldMetadata instanceof TextAreaFieldMetadata) {
      newElement = createTextAreaMetaField((TextAreaFieldMetadata) abstractFieldMetadata);
    } else if (abstractFieldMetadata instanceof BooleanFieldMetadata) {
      newElement = createCheckBoxMetaField((BooleanFieldMetadata) abstractFieldMetadata);
    } else if (abstractFieldMetadata instanceof ComboBoxFieldMetadata) {
      newElement = createComboBoxMetaField((ComboBoxFieldMetadata) abstractFieldMetadata);
    } else if (abstractFieldMetadata instanceof ReferenceFieldMetadata) {
      newElement = createReferenceMetaField((ReferenceFieldMetadata) abstractFieldMetadata);
    } else {
      throw new RuntimeException("Не поддерживаемый тип метеданных " + abstractFieldMetadata.toString());
    }

    return newElement;
  }
  //--------------------------------------------------------------------------------------------------------------------

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
  //--------------------------------------------------------------------------------------------------------------------

  public MetaFieldFactory(GridLayout formLayout
          , Map<String, FormElement> formElementsMap) {
    this.formLayout = formLayout;
    this.formElementsMap = formElementsMap;
  }
  //--------------------------------------------------------------------------------------------------------------------


  /**
   * Добавление ссылочного поля на форму
   *
   * @param fieldMetadata
   * @return
   */
  public FormElement createReferenceMetaField(ReferenceFieldMetadata fieldMetadata) {
    FormElement newElement = new FormElement();
    newElement.fieldType = FieldType.REFERENCE;

    int columnForField = addCaption(newElement, fieldMetadata.caption(), fieldMetadata.column(), fieldMetadata.row(), fieldMetadata.captionCellWidth());

    String fieldName = fieldMetadata.fieldName();
    ReferenceField refField = new ReferenceField(fieldMetadata.entitySelectorFormClass());
    newElement.field = refField;
    newElement.field.setWidth(100, Sizeable.Unit.PERCENTAGE);

    formLayout.addComponent(newElement.field, columnForField, fieldMetadata.row(),
            fieldMetadata.columnEnd(), fieldMetadata.row());

    newElement.field.setId(fieldName);
    formElementsMap.put(fieldName, newElement);

    return newElement;
  }
  //--------------------------------------------------------------------------------------------------------------------


  /**
   * Добавление на форму поля с выпадающим списком
   *
   * @param fieldMetadata
   * @return
   */
  public FormElement createComboBoxMetaField(ComboBoxFieldMetadata fieldMetadata) {
    FormElement newElement = new FormElement();
    newElement.fieldType = FieldType.COMBOBOX;

    int columnForField = addCaption(newElement, fieldMetadata.caption(), fieldMetadata.column(), fieldMetadata.row(), fieldMetadata.captionCellWidth());

    String fieldName = fieldMetadata.fieldName();
    ComboBox cb = new ComboBox();

    cb.setItems(fieldMetadata.enumList().getEnumConstants());
    newElement.field = cb;
    newElement.field.setWidth(100, Sizeable.Unit.PERCENTAGE);

    formLayout.addComponent(newElement.field, columnForField, fieldMetadata.row(),
            fieldMetadata.columnEnd(), fieldMetadata.row());

    newElement.field.setId(fieldName);
    formElementsMap.put(fieldName, newElement);

    return newElement;
  }
  //--------------------------------------------------------------------------------------------------------------------


  /**
   * Добавление на форму логического поля, флажка, переключателя
   *
   * @param fieldMetadata
   * @return
   */
  public FormElement createCheckBoxMetaField(BooleanFieldMetadata fieldMetadata) {
    FormElement newElement = new FormElement();
    newElement.fieldType = FieldType.CHECKBOX;

    int columnForField = addCaption(newElement, fieldMetadata.caption(), fieldMetadata.column(), fieldMetadata.row(), fieldMetadata.captionCellWidth());

    String fieldName = fieldMetadata.fieldName();
    newElement.field = new CheckBox();
    newElement.field.setWidth(100, Sizeable.Unit.PERCENTAGE);

    formLayout.addComponent(newElement.field, columnForField, fieldMetadata.row(),
            columnForField, fieldMetadata.row());

    newElement.field.setId(fieldName);
    formElementsMap.put(fieldName, newElement);

    return newElement;
  }
  //--------------------------------------------------------------------------------------------------------------------


  /**
   * Добавление на форму поля для ввода многострочного текста
   *
   * @param fieldMetadata
   * @return
   */
  public FormElement createTextAreaMetaField(TextAreaFieldMetadata fieldMetadata) {
    FormElement newElement = new FormElement();

    newElement.fieldType = FieldType.MULTI_LINE_TEXT;
    int columnForField = addCaption(newElement, fieldMetadata.caption(), fieldMetadata.column(), fieldMetadata.row(), fieldMetadata.captionCellWidth());

    newElement.field = new TextArea();
    newElement.field.setSizeFull();
    newElement.field.setHeight(fieldMetadata.height(), Sizeable.Unit.PIXELS);

    String fieldName = fieldMetadata.fieldName();

    formLayout.addComponent(newElement.field, columnForField, fieldMetadata.row(),
            fieldMetadata.columnEnd(), fieldMetadata.row());


    newElement.field.setId(fieldName);
    formElementsMap.put(fieldName, newElement);

    return newElement;
  }
  //--------------------------------------------------------------------------------------------------------------------


  /**
   * Добавление на форму поля для ввода  текста
   *
   * @param fieldMetadata
   * @return
   */
  public FormElement createTextMetaField(TextFieldMetadata fieldMetadata) {
    FormElement newElement = new FormElement();

    newElement.fieldType = FieldType.TEXT;
    int columnForField = addCaption(newElement, fieldMetadata.caption(), fieldMetadata.column(), fieldMetadata.row(), fieldMetadata.captionCellWidth());

    String fieldName = fieldMetadata.fieldName();
    newElement.field = new TextField();
    newElement.field.setWidth(100, Sizeable.Unit.PERCENTAGE);

    formLayout.addComponent(newElement.field, columnForField, fieldMetadata.row(),
            fieldMetadata.columnEnd(), fieldMetadata.row());

    newElement.field.setId(fieldName);
    formElementsMap.put(fieldName, newElement);

    return newElement;
  }
}
