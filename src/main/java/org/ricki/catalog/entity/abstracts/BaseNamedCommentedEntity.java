package org.ricki.catalog.entity.abstracts;


import javax.persistence.Column;
import javax.persistence.MappedSuperclass;

@MappedSuperclass
public class BaseNamedCommentedEntity extends BaseEntity {
  @Column(length = 1024, nullable = true)
  private String comment;

  @Column(length = 128, nullable = false, unique = true)
  private String name;

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getComment() {
    return comment;
  }

  public void setComment(String comment) {
    this.comment = comment;
  }

  public String toString() {
    return name;
  }
}
