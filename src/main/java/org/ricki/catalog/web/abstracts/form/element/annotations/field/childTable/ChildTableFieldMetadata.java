package org.ricki.catalog.web.abstracts.form.element.annotations.field.childTable;

import org.ricki.catalog.entity.abstracts.BaseEntity;
import org.ricki.catalog.web.abstracts.form.element.annotations.field.grid.GridMetadata;

import java.lang.annotation.Repeatable;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Retention(RetentionPolicy.RUNTIME)
@Repeatable(ChildTableFieldsMetadata.class)
public @interface ChildTableFieldMetadata {
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
   * высота поля в ячейках
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
  Class<? extends BaseEntity> childClass();

  /**
   * Отображаемые колонки
   *
   * @return
   */
  GridMetadata gridMetadata();
}
