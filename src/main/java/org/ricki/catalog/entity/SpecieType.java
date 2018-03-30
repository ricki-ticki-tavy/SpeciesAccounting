package org.ricki.catalog.entity;

import org.ricki.catalog.entity.abstracts.BaseNamedEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;

/**
 * Виды и описания
 */
@Entity
public class SpecieType extends BaseNamedEntity {

  @Column(nullable = false)
  private int temprDayMin;

  @Column(nullable = false)
  private int temprDayMax;

  @Column(nullable = false)
  private int temprNightMin;

  @Column(nullable = false)
  private int temprNightMax;

  @Column(nullable = false)
  private int humidityMin;

  @Column(nullable = false)
  private int humidityMax;

  @ManyToOne(optional = false)
  private PoisonLevel poisonLevel;

  @ManyToOne(optional = false)
  private SpecieClass specieClass;

  @ManyToOne(optional = false)
  private AggressionLevel aggressionLevel;

  /**
   * линек до взрослого мужского пола
   */
  private int maleMoltingAdult;

  /**
   * лет до взрослого мужского пола
   */
  private Double maleAgeAdult;

  /**
   * линек до взрослого женского пола
   */
  private int femaleMoltingAdult;

  /**
   * лет до взрослого женского пола
   */
  private Double femaleAgeAdult;

  //  @Lob
  private byte[] description;

  public int getTemprDayMin() {
    return temprDayMin;
  }

  public void setTemprDayMin(int temprDayMin) {
    this.temprDayMin = temprDayMin;
  }

  public int getTemprDayMax() {
    return temprDayMax;
  }

  public void setTemprDayMax(int temprDayMax) {
    this.temprDayMax = temprDayMax;
  }

  public int getTemprNightMin() {
    return temprNightMin;
  }

  public void setTemprNightMin(int temprNightMin) {
    this.temprNightMin = temprNightMin;
  }

  public int getTemprNightMax() {
    return temprNightMax;
  }

  public void setTemprNightMax(int temprNightMax) {
    this.temprNightMax = temprNightMax;
  }

  public int getHumidityMin() {
    return humidityMin;
  }

  public void setHumidityMin(int humidityMin) {
    this.humidityMin = humidityMin;
  }

  public int getHumidityMax() {
    return humidityMax;
  }

  public void setHumidityMax(int humidityMax) {
    this.humidityMax = humidityMax;
  }

  public PoisonLevel getPoisonLevel() {
    return poisonLevel;
  }

  public void setPoisonLevel(PoisonLevel poisonLevel) {
    this.poisonLevel = poisonLevel;
  }

  public SpecieClass getSpecieClass() {
    return specieClass;
  }

  public void setSpecieClass(SpecieClass specieClass) {
    this.specieClass = specieClass;
  }

  public AggressionLevel getAggressionLevel() {
    return aggressionLevel;
  }

  public void setAggressionLevel(AggressionLevel aggressionLevel) {
    this.aggressionLevel = aggressionLevel;
  }

  public int getMaleMoltingAdult() {
    return maleMoltingAdult;
  }

  public void setMaleMoltingAdult(int maleMoltingAdult) {
    this.maleMoltingAdult = maleMoltingAdult;
  }

  public Double getMaleAgeAdult() {
    return maleAgeAdult;
  }

  public void setMaleAgeAdult(Double maleAgeAdult) {
    this.maleAgeAdult = maleAgeAdult;
  }

  public int getFemaleMoltingAdult() {
    return femaleMoltingAdult;
  }

  public void setFemaleMoltingAdult(int femaleMoltingAdult) {
    this.femaleMoltingAdult = femaleMoltingAdult;
  }

  public Double getFemaleAgeAdult() {
    return femaleAgeAdult;
  }

  public void setFemaleAgeAdult(Double femaleAgeAdult) {
    this.femaleAgeAdult = femaleAgeAdult;
  }

  public byte[] getDescription() {
    return description;
  }

  public void setDescription(byte[] description) {
    this.description = description;
  }
}
