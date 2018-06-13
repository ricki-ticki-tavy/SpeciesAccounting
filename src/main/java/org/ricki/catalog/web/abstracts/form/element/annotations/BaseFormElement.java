package org.ricki.catalog.web.abstracts.form.element.annotations;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.ANNOTATION_TYPE;

/**
 * Описывает основные характеристики поля
 */
@Target(ANNOTATION_TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface BaseFormElement {
  String name();

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
  int rowEnd();

  /**
   * высота поля в ячейках
   *
   * @return
   */
  int columnEnd();

}
