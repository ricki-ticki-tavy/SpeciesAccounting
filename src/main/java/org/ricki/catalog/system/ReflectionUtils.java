package org.ricki.catalog.system;

import java.lang.reflect.Field;

public class ReflectionUtils {

  public static Field getEntityFieldReflection(Class<?> clazz, String fieldName) {
    while (clazz != null) {
      try {
        Field field = clazz.getDeclaredField(fieldName);
        field.setAccessible(true);
        return field;
      } catch (NoSuchFieldException rex) {
        clazz = clazz.getSuperclass();
      }
    }
    throw new RuntimeException("Field \"" + fieldName + " not found");
  }


}
