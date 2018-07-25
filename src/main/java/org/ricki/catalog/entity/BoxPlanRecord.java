package org.ricki.catalog.entity;

import org.ricki.catalog.entity.abstracts.BaseCommentedEntity;
import org.ricki.catalog.web.page.actions.entity.AnAction;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;

@Entity
public class BoxPlanRecord extends BaseCommentedEntity {

  @ManyToOne(fetch = FetchType.EAGER, optional = false)
  private Box box;

  @ManyToOne(optional = false, fetch = FetchType.EAGER)
  private AnAction action;


}
