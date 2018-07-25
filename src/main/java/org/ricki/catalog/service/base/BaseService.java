package org.ricki.catalog.service.base;

import org.ricki.catalog.entity.abstracts.BaseEntity;

import java.util.List;

public interface BaseService<E extends BaseEntity> {
  E get(long id);

  E create();

  E save(E entity);

  List<E> getList();

  void remove(long id);

  void remove(E entity);
}
