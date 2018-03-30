package org.ricki.catalog.entity.abstracts;


import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@MappedSuperclass
public class BaseEntity implements Serializable {
  @Id
  @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "entity_sequence")
  @SequenceGenerator(name = "entity_sequence")
  private Long id;

  @Column(nullable = false)
  private Date dateTime;

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public Date getDateTime() {
    return dateTime;
  }

  public void setDateTime(Date dateTime) {
    this.dateTime = dateTime;
  }

  public BaseEntity() {
    dateTime = new Date();
  }
}
