package org.ricki.catalog.web.abstracts.form.element.annotations.field.combobox;

import org.ricki.catalog.service.base.BaseNamedEntityFIlteredSelectorService;

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
   * ширина поля в ячейках
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
   * Класс сервиса, предоставляющего список значений. Так же при этом возможно заполнение
   * фильтра отбора выпадающих значений @entityFilter
   *
   * @return
   */
  Class<? extends BaseNamedEntityFIlteredSelectorService> entityClass() default BaseNamedEntityFIlteredSelectorService.class;

  /**
   * Фильтр для отбора значения выпадающего списка, если данные для выпадающего списка выбираются из БД
   *
   * @return
   */
  String entityFilter() default "";

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
