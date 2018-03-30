package org.ricki.catalog.entity;

import org.ricki.catalog.entity.abstracts.BaseNamedEntity;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;

/**
 * Результат выполнения действия
 */
@Entity
public class ActionResult extends BaseNamedEntity {

  @ManyToOne(fetch = FetchType.EAGER, optional = false)
  private UserWebStyle style;

  @ManyToOne(optional = false, fetch = FetchType.LAZY)
  private AnAction action;

  public UserWebStyle getStyle() {
    return style;
  }

  public void setStyle(UserWebStyle style) {
    this.style = style;
  }

  public AnAction getAction() {
    return action;
  }

  public void setAction(AnAction action) {
    this.action = action;
  }

  public ActionResult(String name, UserWebStyle style, AnAction action) {
    this.setName(name);
    this.style = style;
    this.action = action;
  }

  @Override
  public String toString() {
    return getName();
  }


  public ActionResult() {

  }
}
