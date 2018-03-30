package org.ricki.catalog.entity.abstracts;

import org.ricki.catalog.entity.Photo;

import javax.persistence.Column;
import javax.persistence.FetchType;
import javax.persistence.MappedSuperclass;
import javax.persistence.OneToMany;
import java.util.Set;

@MappedSuperclass
public class BaseThing extends BaseNamedCommentedEntity {

  private Double requiredPrice;

  private Double payedPrice;

  /**
   * Запись активна
   */
  private boolean active;

  /**
   * Признак, что это продается
   */
  private boolean forSale;

  /**
   * Опубликовано в каталог для общего доступа
   */
  private boolean published;

  @OneToMany(fetch = FetchType.LAZY, orphanRemoval = true)
  private Set<Photo> photos;

  /**
   * Есть в наличии
   */
  @Column(nullable = false)
  private boolean present;


  public Double getRequiredPrice() {
    return requiredPrice;
  }

  public void setRequiredPrice(Double requiredPrice) {
    this.requiredPrice = requiredPrice;
  }

  public Double getPayedPrice() {
    return payedPrice;
  }

  public void setPayedPrice(Double payedPrice) {
    this.payedPrice = payedPrice;
  }

  public boolean isActive() {
    return active;
  }

  public void setActive(boolean active) {
    this.active = active;
  }

  public boolean isPublished() {
    return published;
  }

  public void setPublished(boolean published) {
    this.published = published;
  }

  public Set<Photo> getPhotos() {
    return photos;
  }

  public void setPhotos(Set<Photo> photos) {
    this.photos = photos;
  }

  public boolean isPresent() {
    return present;
  }

  public void setPresent(boolean present) {
    this.present = present;
  }

  public boolean isForSale() {
    return forSale;
  }

  public void setForSale(boolean forSale) {
    this.forSale = forSale;
  }
}
