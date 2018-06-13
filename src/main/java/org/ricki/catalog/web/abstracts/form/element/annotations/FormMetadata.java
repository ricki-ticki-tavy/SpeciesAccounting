package org.ricki.catalog.web.abstracts.form.element.annotations;

import org.ricki.catalog.service.base.BaseService;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Описание полей и кнопок формы
 */
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface FormMetadata {

  int rowCount();

  int columnCount();

  String caption();

  Class<?> entityClass();

  Class<? extends BaseService> service();
}
