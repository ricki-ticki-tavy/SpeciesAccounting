package org.ricki.catalog.service.base;

import org.ricki.catalog.entity.abstracts.BaseNamedEntity;

public interface BaseNamedEntityService<E extends BaseNamedEntity> extends BaseService<E> {
  E findByName(String name);
}
