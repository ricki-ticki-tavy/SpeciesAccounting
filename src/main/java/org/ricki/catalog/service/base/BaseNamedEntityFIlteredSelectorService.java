package org.ricki.catalog.service.base;

import org.ricki.catalog.entity.abstracts.BaseNamedEntity;

import java.util.List;

/**
 * иньерфейс для выбора списка элементов со строковым фильтром
 *
 * @param <E>
 */
public interface BaseNamedEntityFIlteredSelectorService<E extends BaseNamedEntity> extends BaseNamedEntityService<E> {
  List<E> getList(String filter);
}
