package org.ricki.catalog.web.abstracts.form.element;

import com.vaadin.ui.GridLayout;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Layout;
import org.ricki.catalog.entity.abstracts.BaseEntity;
import org.ricki.catalog.service.base.BaseService;
import org.ricki.catalog.system.PersistentSupport;
import org.ricki.catalog.web.abstracts.form.element.annotations.FormMetadata;
import org.ricki.catalog.web.abstracts.form.element.annotations.field.bool.BooleanFieldMetadata;
import org.ricki.catalog.web.abstracts.form.element.annotations.field.bool.BooleanFieldsMetadata;
import org.ricki.catalog.web.abstracts.form.element.annotations.field.collectionReferencs.CollectionReferenceFieldMetadata;
import org.ricki.catalog.web.abstracts.form.element.annotations.field.collectionReferencs.CollectionReferenceFieldsMetadata;
import org.ricki.catalog.web.abstracts.form.element.annotations.field.combobox.ComboBoxFieldMetadata;
import org.ricki.catalog.web.abstracts.form.element.annotations.field.combobox.ComboBoxFieldsMetadata;
import org.ricki.catalog.web.abstracts.form.element.annotations.field.reference.ReferenceFieldMetadata;
import org.ricki.catalog.web.abstracts.form.element.annotations.field.reference.ReferenceFieldsMetadata;
import org.ricki.catalog.web.abstracts.form.element.annotations.field.text.TextFieldMetadata;
import org.ricki.catalog.web.abstracts.form.element.annotations.field.text.TextFieldsMetadata;
import org.ricki.catalog.web.abstracts.form.element.annotations.field.textArea.TextAreaFieldMetadata;
import org.ricki.catalog.web.abstracts.form.element.annotations.field.textArea.TextAreaFieldsMetadata;
import org.ricki.catalog.web.abstracts.form.list.BaseListForm;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.web.context.support.SpringBeanAutowiringSupport;

import javax.inject.Inject;
import java.lang.annotation.Annotation;
import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.Map;

public abstract class MetadataForm<E extends BaseEntity> extends BaseEditFormWithToolBar {

  private Map<String, FormElement> formElementsMap;

  private GridLayout formLayout;

  protected E entity;

  protected BaseListForm parentListForm;

  protected BaseService service;

  protected MetaDataFieldFactory metaFieldFactory;
  @Inject
  BeanFactory beanFactory;

  @Inject
  PersistentSupport<E> persistentSupport;

  private void parseFieldAnnotations(Object[] annotations) {
    for (Object abstractFieldMetadata : annotations) {
      metaFieldFactory.createMetaFieldFromMetaDate((Annotation) abstractFieldMetadata);
    }
  }
  //--------------------------------------------------------------------------------------------------------------------

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
  //--------------------------------------------------------------------------------------------------------------------

  private void createAllFields() {
    processFieldAnnotationsByType(BooleanFieldsMetadata.class, BooleanFieldMetadata.class);
    processFieldAnnotationsByType(TextFieldsMetadata.class, TextFieldMetadata.class);
    processFieldAnnotationsByType(TextAreaFieldsMetadata.class, TextAreaFieldMetadata.class);
    processFieldAnnotationsByType(ComboBoxFieldsMetadata.class, ComboBoxFieldMetadata.class);
    processFieldAnnotationsByType(ReferenceFieldsMetadata.class, ReferenceFieldMetadata.class);
    processFieldAnnotationsByType(CollectionReferenceFieldsMetadata.class, CollectionReferenceFieldMetadata.class);
  }
  //--------------------------------------------------------------------------------------------------------------------

  @Override
  public Layout buildContent() {
    FormMetadata formMetadata = this.getClass().getDeclaredAnnotation(FormMetadata.class);

    formLayout = new GridLayout();
    formLayout.setSizeFull();
    formLayout.setSpacing(true);
    formElementsMap = new HashMap<>();

    metaFieldFactory = new MetaDataFieldFactory(formLayout, formElementsMap);

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
  //--------------------------------------------------------------------------------------------------------------------

  public E load(long id) {
    entity = (E) service.get(id);
    return entity;
  }
  //--------------------------------------------------------------------------------------------------------------------

  public void fillForm() {
    fillForm(null);
  }
  //--------------------------------------------------------------------------------------------------------------------

  public void fillForm(E entity_) {
    if (entity_ != null) {
      this.entity = entity_;
    }

    this.entity = persistentSupport.fillFormData(formElementsMap, entity);
//    for (String fieldName : formElementsMap.keySet()) {
//      FormElement formElement = formElementsMap.get(fieldName);
//      field = ReflectionUtils.getEntityFieldReflection(entity.getClass(), fieldName);
//
//      try {
//        formElement.setValue(field.get(entity));
//      } catch (IllegalAccessException rex) {
//        throw new RuntimeException(rex);
//      }
//    }
  }
  //--------------------------------------------------------------------------------------------------------------------

  public final E save() {
    boolean isNew = false;
    if (entity == null) {
      entity = (E) service.create();
      isNew = true;
    }
    for (String fieldName : formElementsMap.keySet()) {
      formElementsMap.get(fieldName).readValueToEntity(fieldName, entity);
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
  //--------------------------------------------------------------------------------------------------------------------

  public void setParentListForm(BaseListForm parentListForm) {
    this.parentListForm = parentListForm;
  }
  //--------------------------------------------------------------------------------------------------------------------

}
