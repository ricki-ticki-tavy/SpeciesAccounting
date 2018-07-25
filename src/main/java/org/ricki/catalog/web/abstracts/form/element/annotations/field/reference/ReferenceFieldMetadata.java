package org.ricki.catalog.web.abstracts.form.element.annotations.field.reference;

import org.ricki.catalog.entity.abstracts.BaseEntity;
import org.ricki.catalog.web.abstracts.form.element.annotations.field.grid.GridMetadata;

import java.lang.annotation.Repeatable;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Retention(RetentionPolicy.RUNTIME)
@Repeatable(ReferenceFieldsMetadata.class)
public @interface ReferenceFieldMetadata {
  String fieldName();

  String caption() default "";

  /**
   * Начальная строка поля
   *
   * @return
   */
  int row();

  /**
   * Начальная колонка поля
   *
   * @return
   */
  int column();

  /**
   * Ширина поля в колонках
   *
   * @return
   */
  int height();

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
  Class<? extends BaseEntity> childClass();

  /**
   * Отображаемые колонки
   *
   * @return
   */
  GridMetadata gridMetadata();
}
