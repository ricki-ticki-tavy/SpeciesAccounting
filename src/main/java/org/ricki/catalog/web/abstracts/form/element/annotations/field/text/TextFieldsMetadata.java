package org.ricki.catalog.web.abstracts.form.element.annotations.field.text;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface TextFieldsMetadata {
  TextFieldMetadata[] value();
}
