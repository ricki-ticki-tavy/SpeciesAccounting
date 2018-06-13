package org.ricki.catalog.entity;

import org.ricki.catalog.entity.abstracts.BaseNamedCommentedEntity;

import javax.persistence.Column;
import javax.persistence.Entity;

@Entity
public class Photo extends BaseNamedCommentedEntity {

  //  @Column(nullable = false)
//  private Date dateTime;
//
  @Column(length = 128, nullable = true)
  private String alt;

  //  @Lob
  private byte[] image;

  @Column(nullable = false)
  private int sortOrder;

  /**
   * Опубликовано в каталог для общего доступа
   */
  private boolean published;

  public boolean isPublished() {
    return published;
  }

  public void setPublished(boolean published) {
    this.published = published;
  }

  public String getAlt() {
    return alt;
  }

  public void setAlt(String alt) {
    this.alt = alt;
  }

  public byte[] getImage() {
    return image;
  }

  public void setImage(byte[] image) {
    this.image = image;
  }

  public int getSortOrder() {
    return sortOrder;
  }

  public void setSortOrder(int sortOrder) {
    this.sortOrder = sortOrder;
  }
}
