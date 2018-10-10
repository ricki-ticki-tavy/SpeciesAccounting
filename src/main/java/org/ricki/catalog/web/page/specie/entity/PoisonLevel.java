package org.ricki.catalog.web.page.specie.entity;

import org.ricki.catalog.entity.abstracts.BaseNamedEntity;
import org.ricki.catalog.web.page.styles.entity.UserWebStyle;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class PoisonLevel extends BaseNamedEntity {
  @ManyToOne
  @JoinColumn(nullable = false)
  private UserWebStyle userWebStyle;

  /**
   * Баллы опасности
   */
  @Column(nullable = false)
  private int balls;

  public UserWebStyle getUserWebStyle() {
    return userWebStyle;
  }

  public void setUserWebStyle(UserWebStyle userWebStyle) {
    this.userWebStyle = userWebStyle;
  }

  public int getBalls() {
    return balls;
  }

  public void setBalls(int balls) {
    this.balls = balls;
  }

  public PoisonLevel() {

  }

  public PoisonLevel(String name, int balls, UserWebStyle style) {
    this.balls = balls;
    this.userWebStyle = style;
    setName(name);
  }

}
