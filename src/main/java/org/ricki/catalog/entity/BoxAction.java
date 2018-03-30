package org.ricki.catalog.entity;

import org.ricki.catalog.entity.abstracts.BaseThingAction;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;

/**
 * Действия с террариумом
 */
@Entity
public class BoxAction extends BaseThingAction {

  @ManyToOne(optional = false, fetch = FetchType.LAZY)
  private Box box;


  public Box getBox() {
    return box;
  }

  public void setBox(Box box) {
    this.box = box;
  }
}
