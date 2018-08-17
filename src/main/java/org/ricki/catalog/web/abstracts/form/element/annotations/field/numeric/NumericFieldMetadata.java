package org.ricki.catalog.web.abstracts.form.element.annotations.field.numeric;

import java.lang.annotation.*;


@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE})
@Repeatable(NumericFieldsMetadata.class)
public @interface NumericFieldMetadata {
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
   * Формат (0.##) например
   *
   * @return
   */
  String format();
}
