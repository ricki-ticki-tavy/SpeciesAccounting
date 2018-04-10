package org.ricki.catalog.entity.abstracts;


import javax.persistence.Column;
import javax.persistence.MappedSuperclass;

@MappedSuperclass
public class BaseNamedEntity extends BaseEntity {
  @Column(length = 128, nullable = false, unique = true)
  private String name;

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String toString() {
    return name;
  }
}
