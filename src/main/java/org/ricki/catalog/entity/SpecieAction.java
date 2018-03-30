package org.ricki.catalog.entity;

import org.ricki.catalog.entity.abstracts.BaseThingAction;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;

/**
 * Действия с особями
 */
@Entity
public class SpecieAction extends BaseThingAction {

  @ManyToOne(optional = false, fetch = FetchType.LAZY)
  private Specie specie;

  public Specie getSpecie() {
    return specie;
  }

  public void setSpecie(Specie specie) {
    this.specie = specie;
  }
}
