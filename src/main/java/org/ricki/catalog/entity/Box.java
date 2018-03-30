package org.ricki.catalog.entity;


import org.ricki.catalog.entity.abstracts.BaseThing;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import java.util.Set;

/**
 * Контейнеры, терры
 */
@Entity
public class Box extends BaseThing {

  @Column(length = 128, nullable = false)
  private String info;

  /**
   * Действия. Кормление, например
   */
  @OneToMany(fetch = FetchType.LAZY)
  private Set<BoxAction> actions;

  public String getInfo() {
    return info;
  }

  public void setInfo(String info) {
    this.info = info;
  }

  public Set<BoxAction> getActions() {
    return actions;
  }

  public void setActions(Set<BoxAction> actions) {
    this.actions = actions;
  }
}
