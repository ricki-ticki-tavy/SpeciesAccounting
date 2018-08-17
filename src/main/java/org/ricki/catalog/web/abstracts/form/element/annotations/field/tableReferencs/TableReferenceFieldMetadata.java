package org.ricki.catalog.web.abstracts.form.element.annotations.field.tableReferencs;

import org.ricki.catalog.entity.abstracts.BaseEntity;
import org.ricki.catalog.web.abstracts.form.element.BaseEditForm;
import org.ricki.catalog.web.abstracts.form.element.annotations.field.grid.GridMetadata;
import org.ricki.catalog.web.abstracts.form.list.BaseListForm;

import java.lang.annotation.Repeatable;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Retention(RetentionPolicy.RUNTIME)
@Repeatable(TableReferenceFieldsMetadata.class)
public @interface TableReferenceFieldMetadata {
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
  Class<? extends BaseEditForm> entityEditorFormClass() default NULL_EDIT_FORM.class;

  /**
   * класс окна для формы списка из которого выбирать запись
   *
   * @return
   */
  Class<? extends BaseListForm> entityListFormClass() default NULL_LIST_FORM.class;

  /**
   * Колонки для отображаемой таблицы
   *
   * @return
   */
  GridMetadata gridMetadata();

  static final BaseEditForm DEFAULT = null;

  public abstract class NULL_EDIT_FORM extends BaseEditForm {
  }

  public abstract class NULL_LIST_FORM extends BaseListForm {
  }

}
