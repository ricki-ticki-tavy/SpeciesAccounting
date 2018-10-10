package org.ricki.catalog.web.page.specie.entity;

import org.ricki.catalog.entity.abstracts.BaseNamedEntity;

import javax.persistence.Entity;

/**
 * Типы питомцев
 */
@Entity
public class SpecieClass extends BaseNamedEntity {
  public SpecieClass() {

  }

  public SpecieClass(String name) {
    setName(name);
  }
}
