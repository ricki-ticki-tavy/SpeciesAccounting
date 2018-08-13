package org.ricki.catalog.web.abstracts.form.element.annotations.field.textArea;

import java.lang.annotation.Repeatable;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Retention(RetentionPolicy.RUNTIME)
@Repeatable(TextAreaFieldsMetadata.class)
public @interface TextAreaFieldMetadata {
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
}
