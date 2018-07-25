package org.ricki.catalog.web.abstracts.form.element.annotations.field.bool;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface BooleanFieldsMetadata {
  BooleanFieldMetadata[] value();
}
