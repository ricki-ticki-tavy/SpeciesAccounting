package org.ricki.catalog.web.abstracts.form.element;

import com.vaadin.ui.*;
import org.ricki.catalog.entity.abstracts.BaseEntity;
import org.ricki.catalog.service.base.BaseService;
import org.ricki.catalog.web.abstracts.form.common.Styles;
import org.ricki.catalog.web.abstracts.form.element.annotations.FormMetadata;
import org.ricki.catalog.web.abstracts.form.element.annotations.field.area.TextAreaFieldMetadata;
import org.ricki.catalog.web.abstracts.form.element.annotations.field.area.TextAreaFieldsMetadata;
import org.ricki.catalog.web.abstracts.form.element.annotations.field.text.TextFieldMetadata;
import org.ricki.catalog.web.abstracts.form.element.annotations.field.text.TextFieldsMetadata;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.util.StringUtils;
import org.springframework.web.context.support.SpringBeanAutowiringSupport;

import javax.inject.Inject;
import java.lang.annotation.Annotation;
import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.Map;

public abstract class MetadataForm<E extends BaseEntity> extends BaseEditFormWithToolBar {

  private Map<String, FormElement> elementsMap;

  private GridLayout formLayout;

  protected E entity;

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
        TextFieldMetadata fieldMetadata = (TextFieldMetadata) abstractFieldMetadata;
        int columnForField = addCaption(newElement, fieldMetadata.caption(), fieldMetadata.column(), fieldMetadata.row(), fieldMetadata.captionCellWidth());

        fieldName = fieldMetadata.fieldName();
        newElement.field = new TextField();
        newElement.field.setWidth(100, Unit.PERCENTAGE);

        formLayout.addComponent(newElement.field, columnForField, fieldMetadata.row(),
                fieldMetadata.columnEnd(), fieldMetadata.row());
      } else if (abstractFieldMetadata instanceof TextAreaFieldMetadata) {
        TextAreaFieldMetadata fieldMetadata = (TextAreaFieldMetadata) abstractFieldMetadata;
        int columnForField = addCaption(newElement, fieldMetadata.caption(), fieldMetadata.column(), fieldMetadata.row(), fieldMetadata.captionCellWidth());

        newElement.field = new TextArea();
        newElement.field.setSizeFull();
        newElement.field.setHeight(fieldMetadata.height(), Unit.PIXELS);
        fieldName = fieldMetadata.fieldName();

        formLayout.addComponent(newElement.field, columnForField, fieldMetadata.row(),
                fieldMetadata.columnEnd(), fieldMetadata.row());

      }

      if (!StringUtils.isEmpty(fieldName)) {
        newElement.field.setId(fieldName);
        elementsMap.put(fieldName, newElement);
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
    processFieldAnnotationsByType(TextFieldsMetadata.class, TextFieldMetadata.class);
    processFieldAnnotationsByType(TextAreaFieldsMetadata.class, TextAreaFieldMetadata.class);
  }

  @Override
  public Layout buildContent() {
    FormMetadata formMetadata = this.getClass().getDeclaredAnnotation(FormMetadata.class);

    formLayout = new GridLayout();
    formLayout.setSizeFull();
    formLayout.setSpacing(true);

    elementsMap = new HashMap<>();

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

}
