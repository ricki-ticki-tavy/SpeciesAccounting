package org.ricki.catalog.entity;


import org.ricki.catalog.entity.abstracts.BaseThing;

import javax.persistence.Column;
import javax.persistence.Entity;

/**
 * КО
 */
@Entity
public class Food extends BaseThing {

  @Column(nullable = true)
  private double quantity;

  public double getQuantity() {
    return quantity;
  }

  public void setQuantity(double quantity) {
    this.quantity = quantity;
  }

}
