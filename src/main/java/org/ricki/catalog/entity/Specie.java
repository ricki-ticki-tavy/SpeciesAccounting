package org.ricki.catalog.entity;

import org.ricki.catalog.entity.abstracts.BaseThing;

import javax.persistence.*;
import java.util.Set;

/**
 * Питомцы
 */
@Entity
public class Specie extends BaseThing {

  @ManyToOne(optional = false, fetch = FetchType.EAGER)
  private SpecieType specieType;

  /**
   * Кол-во линек
   */
  private int moltCountMin;

  /**
   * Кол-во линек
   */
  private int moltCountMax;

  /**
   * интервал кормления в днях
   */
  private int feedInterval;

  @ManyToOne(optional = true)
  private Box box;

  @OneToMany(orphanRemoval = true, fetch = FetchType.EAGER, mappedBy = "specie")
  private Set<SpecieAction> actions;

  @Column(nullable = false)
  private Gender gender;

  public SpecieType getSpecieType() {
    return specieType;
  }

  public void setSpecieType(SpecieType specieType) {
    this.specieType = specieType;
  }

  public int getMoltCountMin() {
    return moltCountMin;
  }

  public void setMoltCountMin(int moltCountMin) {
    this.moltCountMin = moltCountMin;
  }

  public int getFeedInterval() {
    return feedInterval;
  }

  public void setFeedInterval(int feedInterval) {
    this.feedInterval = feedInterval;
  }

  public Box getBox() {
    return box;
  }

  public void setBox(Box box) {
    this.box = box;
  }

  public Set<SpecieAction> getActions() {
    return actions;
  }

  public void setActions(Set<SpecieAction> actions) {
    this.actions = actions;
  }

  public int getMoltCountMax() {
    return moltCountMax;
  }

  public void setMoltCountMax(int moltCountMax) {
    this.moltCountMax = moltCountMax;
  }

  public enum Gender {
    MALE("Самец"),
    FEMALE("Самка");

    private String caption;

    private Gender(String caption) {
      this.caption = caption;
    }

    public String getCaption() {
      return caption;
    }
  }
}
