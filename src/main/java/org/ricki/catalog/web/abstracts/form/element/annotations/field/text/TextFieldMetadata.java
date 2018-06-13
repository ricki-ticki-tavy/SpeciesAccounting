package org.ricki.catalog.web.abstracts.form.element.annotations.field.text;

import java.lang.annotation.*;


@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE})
@Repeatable(TextFieldsMetadata.class)
public @interface TextFieldMetadata {
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
}
