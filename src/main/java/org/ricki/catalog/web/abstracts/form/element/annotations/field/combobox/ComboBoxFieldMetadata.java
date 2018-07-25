package org.ricki.catalog.web.abstracts.form.element.annotations.field.combobox;

import java.lang.annotation.Repeatable;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Retention(RetentionPolicy.RUNTIME)
@Repeatable(ComboBoxFieldsMetadata.class)
public @interface ComboBoxFieldMetadata {
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
   * Класс списка значений (enum)
   *
   * @return
   */
  Class<? extends Enum> enumList();

  /**
   * свободный ввод запрещен. Выбор только из списка
   *
   * @return
   */
  boolean fixedList() default true;
}
