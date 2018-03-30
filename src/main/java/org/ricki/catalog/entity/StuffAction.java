package org.ricki.catalog.entity;

import org.ricki.catalog.entity.abstracts.BaseThingAction;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;

/**
 * Действия с оборудованием
 */
@Entity
public class StuffAction extends BaseThingAction {

  @ManyToOne(optional = false, fetch = FetchType.LAZY)
  private Stuff stuff;

  public Stuff getStuff() {
    return stuff;
  }

  public void setStuff(Stuff stuff) {
    this.stuff = stuff;
  }
}
