package org.ricki.catalog.entity;

import org.ricki.catalog.entity.abstracts.BaseCommentedEntity;

import javax.persistence.Column;
import javax.persistence.ManyToOne;

/**
 * Продажи
 */
public class Sale extends BaseCommentedEntity {
  @Column(nullable = false)
  private double cost;

  @ManyToOne(optional = true)
  private Stuff stuff;

  @ManyToOne(optional = true)
  private Specie specie;

  @ManyToOne(optional = true)
  private Box box;

  public double getCost() {
    return cost;
  }

  public void setCost(double cost) {
    this.cost = cost;
  }

  public Stuff getStuff() {
    return stuff;
  }

  public void setStuff(Stuff stuff) {
    this.stuff = stuff;
  }

  public Specie getSpecie() {
    return specie;
  }

  public void setSpecie(Specie specie) {
    this.specie = specie;
  }
}
