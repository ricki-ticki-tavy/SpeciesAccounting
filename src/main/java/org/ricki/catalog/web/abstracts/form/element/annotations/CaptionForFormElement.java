package org.ricki.catalog.web.abstracts.form.element.annotations;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({})
@Retention(RetentionPolicy.RUNTIME)
public @interface CaptionForFormElement {
  /**
   * Заголовок поля. Если null или пусто, то заголовок не будет обрабатываться
   *
   * @return
   */
  String caption() default "";

  /**
   * Строка расположения заголовка поля
   *
   * @return
   */
  int row() default 0;

  /**
   * Начальная колонка расположения заголовка поля
   *
   * @return
   */
  int column() default 0;

  /**
   * ширина заголовка поля в колонках
   *
   * @return
   */
  int columnEnd() default 0;

}
