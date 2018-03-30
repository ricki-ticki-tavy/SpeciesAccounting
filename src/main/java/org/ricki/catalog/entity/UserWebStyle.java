package org.ricki.catalog.entity;

import org.ricki.catalog.entity.abstracts.BaseNamedCommentedEntity;

import javax.persistence.Column;
import javax.persistence.Entity;

@Entity
public class UserWebStyle extends BaseNamedCommentedEntity {

  @Column(length = 128, nullable = false)
  private String systemName;

  @Column(length = 256, nullable = true)
  private String styleText;

  public String getStyleText() {
    return styleText;
  }

  public void setStyleText(String styleText) {
    this.styleText = styleText;
  }

  public UserWebStyle(String name, String systemName, String comment, String styleText) {
    this.systemName = systemName;
    this.styleText = styleText;
    this.setName(name);
    this.setComment(comment);
  }

  @Override
  public String toString() {
    return getName();
  }

  public UserWebStyle() {
  }

  public String getSystemName() {
    return systemName;
  }

  public void setSystemName(String systemName) {
    this.systemName = systemName;
  }
}
