package org.ricki.catalog.web.abstracts.form.element.annotations;

import java.lang.annotation.Annotation;

public class FieldMetadataInterface implements FieldMetadata {
  @Override
  public Class<? extends Annotation> annotationType() {
    return FieldMetadata.class;
  }
}
