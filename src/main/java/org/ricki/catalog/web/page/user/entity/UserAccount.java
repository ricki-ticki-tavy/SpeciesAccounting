package org.ricki.catalog.web.page.user.entity;

import org.ricki.catalog.entity.abstracts.BaseNamedCommentedEntity;

import javax.persistence.Column;
import javax.persistence.Entity;

@Entity
public class UserAccount extends BaseNamedCommentedEntity {
  /**
   * Hash
   */
  @Column(nullable = false)
  String password;

  boolean locked;

  @Column(length = 256)
  String lockReason;

  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }

  public boolean isLocked() {
    return locked;
  }

  public void setLocked(boolean locked) {
    this.locked = locked;
  }

  public String getLockReason() {
    return lockReason;
  }

  public void setLockReason(String lockReason) {
    this.lockReason = lockReason;
  }

  public UserAccount() {

  }

  public UserAccount(String name, String comment, String password, boolean locked, String lockReason) {
    this.setName(name);
    this.setComment(comment);
    this.password = password;
    this.locked = locked;
    this.lockReason = lockReason;
  }
}
