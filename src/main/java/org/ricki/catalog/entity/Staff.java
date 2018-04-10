package org.ricki.catalog.entity;

import org.ricki.catalog.entity.abstracts.BaseThing;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import java.util.Set;

@Entity
public class Staff extends BaseThing {
  @OneToMany(fetch = FetchType.LAZY)
  private Set<StuffAction> actions;

  public Set<StuffAction> getActions() {
    return actions;
  }

  public void setActions(Set<StuffAction> actions) {
    this.actions = actions;
  }

}
