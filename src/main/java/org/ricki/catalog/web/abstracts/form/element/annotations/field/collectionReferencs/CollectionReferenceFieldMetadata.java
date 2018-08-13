package org.ricki.catalog.web.abstracts.form.element.annotations.field.collectionReferencs;

import org.ricki.catalog.entity.abstracts.BaseEntity;
import org.ricki.catalog.web.abstracts.form.element.BaseEditForm;
import org.ricki.catalog.web.abstracts.form.element.annotations.field.grid.GridMetadata;

import java.lang.annotation.Repeatable;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Retention(RetentionPolicy.RUNTIME)
@Repeatable(CollectionReferenceFieldsMetadata.class)
public @interface CollectionReferenceFieldMetadata {
  String fieldName();

  String caption() default "";

  /**
   * Начальная строка поля
   *
   * @return
   */
  int row();

  /**
   * Высота поля в строках
   *
   * @return
   */
  int height();

  /**
   * Начальная колонка поля
   *
   * @return
   */
  int column();

  /**
   * последняя колонка поля
   *
   * @return
   */
  int columnEnd();

  /**
   * ширина подписи в ячейках
   *
   * @return
   */
  int captionCellWidth();

  /**
   * Класс сущности для списка
   *
   * @return
   */
  Class<? extends BaseEntity> entityClass();

  /**
   * класс окна для формы редактирования / добавления элемента в таблицу
   *
   * @return
   */
  Class<? extends BaseEditForm> entityEditorFormClass();

  /**
   * Колонки для отображаемой таблицы
   *
   * @return
   */
  GridMetadata gridMetadata();

}
