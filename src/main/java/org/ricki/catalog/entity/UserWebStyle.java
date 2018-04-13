package org.ricki.catalog.entity;

import org.ricki.catalog.entity.abstracts.BaseNamedCommentedEntity;
import org.ricki.catalog.entity.abstracts.SystemRecordDetectable;

import javax.persistence.Column;
import javax.persistence.Entity;

@Entity
public class UserWebStyle extends BaseNamedCommentedEntity implements SystemRecordDetectable {

  @Column(length = 128, nullable = false)
  private String systemName;

  @Column(length = 256, nullable = true)
  private String styleText;

  @Column(nullable = false)
  private boolean system;

  public String getStyleText() {
    return styleText;
  }

  public void setStyleText(String styleText) {
    this.styleText = styleText;
  }

  public UserWebStyle(String name, String systemName, String comment, String styleText, boolean system) {
    this.systemName = systemName;
    this.styleText = styleText;
    this.system = system;
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

  @Override
  public boolean isSystem() {
    return system;
  }

  public void setSystem(boolean system) {
    this.system = system;
  }
}
