package org.ricki.catalog.system;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.ricki.catalog.web.abstracts.form.element.FormElement;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;

import javax.inject.Inject;
import javax.inject.Named;
import javax.transaction.Transactional;
import java.lang.reflect.Field;
import java.util.Map;

@Named
@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
@Transactional
public class PersistentSupport<E> {
  @Inject
  SessionFactory sessionFactory;

  /**
   * Переносит на форму значения из сущности. В том числе LAZY поля
   *
   * @param formElementsMap
   * @param entity
   * @return
   */
  public E fillFormData(Map<String, FormElement> formElementsMap, E entity) {
    Field field;

    Session session = sessionFactory.getCurrentSession();
    entity = (E) session.merge(entity);

    for (String fieldName : formElementsMap.keySet()) {
      FormElement formElement = formElementsMap.get(fieldName);
      field = ReflectionUtils.getEntityFieldReflection(entity.getClass(), fieldName);

      try {
        formElement.setValue(field.get(entity));
      } catch (IllegalAccessException rex) {
        throw new RuntimeException(rex);
      }
    }
    return entity;
  }
  //--------------------------------------------------------------------------------------------------------------------


}
