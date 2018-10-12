package org.ricki.catalog.web.page.action.entity;

import org.ricki.catalog.entity.abstracts.BaseNamedEntity;
import org.ricki.catalog.web.page.styles.entity.UserWebStyle;

import javax.persistence.*;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 * Результат выполнения действия
 */
@Entity
public class ActionResult extends BaseNamedEntity {

  @ManyToOne(fetch = FetchType.EAGER, optional = false, cascade = {CascadeType.PERSIST, CascadeType.DETACH, CascadeType.MERGE, CascadeType.REFRESH})
  private UserWebStyle style;

  @ManyToMany(fetch = FetchType.LAZY)
  private Set<AnAction> actionSet;

  public UserWebStyle getStyle() {
    return style;
  }

  public void setStyle(UserWebStyle style) {
    this.style = style;
  }

  public Set<AnAction> getActionSet() {
    return actionSet;
  }

  public void setActionSet(Set<AnAction> actionSet) {
    this.actionSet = actionSet;
  }

  public ActionResult(String name, UserWebStyle style) {
    this.setName(name);
    this.style = style;
    this.actionSet = null;
  }

  public ActionResult(String name, UserWebStyle style, Set<AnAction> actionSet) {
    this.setName(name);
    this.style = style;
    this.actionSet = actionSet;
  }

  public ActionResult(String name, UserWebStyle style, AnAction[] actionArray) {
    Set<AnAction> actionSet = new HashSet<>(actionArray.length);
    Arrays.stream(actionArray).forEach(anAction -> actionSet.add(anAction));
    this.setName(name);
    this.style = style;
    this.actionSet = actionSet;
  }

  public ActionResult(String name, UserWebStyle style, AnAction singleAction) {
    this(name, style, new AnAction[]{singleAction});
  }

  @Override
  public String toString() {
    return getName();
  }


  public ActionResult() {

  }
}
