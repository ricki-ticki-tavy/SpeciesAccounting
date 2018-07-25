package org.ricki.catalog.entity.abstracts;

import org.ricki.catalog.web.page.actions.entity.ActionResult;
import org.ricki.catalog.web.page.actions.entity.AnAction;

import javax.persistence.Column;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import javax.persistence.MappedSuperclass;

@MappedSuperclass
public class BaseThingAction extends BaseCommentedEntity {

  @ManyToOne(optional = false, fetch = FetchType.EAGER)
  private AnAction action;

  @ManyToOne(optional = true)
  private ActionResult actionResult;

  @Column(nullable = false)
  private int timeCost;

  public AnAction getAction() {
    return action;
  }

  public void setAction(AnAction action) {
    this.action = action;
  }

  public ActionResult getActionResult() {
    return actionResult;
  }

  public void setActionResult(ActionResult actionResult) {
    this.actionResult = actionResult;
  }

  public int getTimeCost() {
    return timeCost;
  }

  public void setTimeCost(int timeCost) {
    this.timeCost = timeCost;
  }
}


