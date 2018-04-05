package org.ricki.catalog.entity;


import org.ricki.catalog.entity.abstracts.BaseEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

/**
 * Контейнеры, терры
 */
@Entity
@Table(uniqueConstraints = {@UniqueConstraint(name = "sys_set_name_owner", columnNames = {"name", "owner"})})
public class SystemSetting extends BaseEntity {

  @Column(nullable = false, length = 128)
  private String name;

  @Column(nullable = true)
  private byte[] longData;

  @Column(nullable = false)
  private String owner;

  @Column(length = 128, nullable = true)
  private String shortData;

  public byte[] getLongData() {
    return longData;
  }

  public String getOwner() {
    return owner;
  }

  public String getShortData() {
    return shortData;
  }

  public void setLongData(byte[] longData) {
    this.longData = longData;
  }

  public void setOwner(String owner) {
    this.owner = owner;
  }

  public void setShortData(String shortData) {
    this.shortData = shortData;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }
}
