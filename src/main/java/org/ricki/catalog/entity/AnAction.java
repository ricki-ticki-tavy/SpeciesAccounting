package org.ricki.catalog.entity;

import org.ricki.catalog.entity.abstracts.BaseNamedCommentedEntity;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table
public class AnAction extends BaseNamedCommentedEntity {
  @ManyToOne(optional = false, fetch = FetchType.EAGER)
  private UserWebStyle planedStyle;

  @Column(nullable = false)
  private boolean system;

  @OneToMany(fetch = FetchType.LAZY, orphanRemoval = true, cascade = CascadeType.ALL)
  private Set<ActionResult> availableResults;

  @Column(nullable = false)
  private ActionRecipient actionRecipient;

  public UserWebStyle getPlanedStyle() {
    return planedStyle;
  }

  public void setPlanedStyle(UserWebStyle planedStyle) {
    this.planedStyle = planedStyle;
  }

  public boolean isSystem() {
    return system;
  }

  public void setSystem(boolean system) {
    this.system = system;
  }

  public Set<ActionResult> getAvailableResults() {
    return availableResults;
  }

  public void setAvailableResults(Set<ActionResult> availableResults) {
    this.availableResults = availableResults;
  }

  public ActionRecipient getActionRecipient() {
    return actionRecipient;
  }

  public void setActionRecipient(ActionRecipient actionRecipient) {
    this.actionRecipient = actionRecipient;
  }

  public AnAction(String name, String comment, UserWebStyle planedStyle, boolean system, ActionRecipient actionRecipient) {
    setName(name);
    setComment(comment);
    setPlanedStyle(planedStyle);
    setSystem(system);
    setActionRecipient(actionRecipient);
    setAvailableResults(new HashSet<>());
  }

  @Override
  public String toString() {
    return getName();
  }

  public AnAction() {

  }

  public enum ActionRecipient {
    SPECIE("Особь"),
    BOX("Террариум"),
    STUFF("Оборудование");
    private String caption;

    @Override
    public String toString() {
      return caption;
    }

    private ActionRecipient(String caption) {
      this.caption = caption;
    }

    public String getCaption() {
      return caption;
    }
  }
}