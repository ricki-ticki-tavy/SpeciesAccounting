package org.ricki.catalog.entity.metadata.annotation;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({})
@Retention(RetentionPolicy.RUNTIME)
public @interface ColumnInfo {
  String fieldName();

  String columnCaption();

  int width();

  int graphicHeight() default 0;
}
