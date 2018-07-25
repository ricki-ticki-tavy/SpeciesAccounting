package org.ricki.catalog.web.abstracts.form.element.annotations.field.bool;

import java.lang.annotation.*;


@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE})
@Repeatable(BooleanFieldsMetadata.class)
public @interface BooleanFieldMetadata {
  String fieldName() default "";

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
   * ширина подписи в ячейках
   *
   * @return
   */
  int captionCellWidth();
}
