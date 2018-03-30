package org.ricki.catalog.entity;

import org.ricki.catalog.entity.abstracts.BaseCommentedEntity;

import javax.persistence.Column;
import java.util.Date;

/**
 * Затраты. Различные
 */
public class Expenditure extends BaseCommentedEntity {

  @Column(nullable = false)
  private double cost;

  @Column(nullable = true)
  private Date untilDate;

  public double getCost() {
    return cost;
  }

  public void setCost(double cost) {
    this.cost = cost;
  }

  public Date getUntilDate() {
    return untilDate;
  }

  public void setUntilDate(Date untilDate) {
    this.untilDate = untilDate;
  }
}
