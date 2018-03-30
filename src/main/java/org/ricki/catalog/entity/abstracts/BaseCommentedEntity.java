package org.ricki.catalog.entity.abstracts;


import javax.persistence.Column;
import javax.persistence.MappedSuperclass;

@MappedSuperclass
public class BaseCommentedEntity extends BaseEntity {
  @Column(length = 1024, nullable = true)
  private String comment;

  public String getComment() {
    return comment;
  }

  public void setComment(String comment) {
    this.comment = comment;
  }
}
