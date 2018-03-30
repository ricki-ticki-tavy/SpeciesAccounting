package org.ricki.catalog.entity;


import org.ricki.catalog.entity.abstracts.BaseNamedEntity;

import javax.persistence.Column;
import javax.persistence.Entity;

/**
 * Контейнеры, терры
 */
@Entity
public class SystemSetting extends BaseNamedEntity {

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
}
