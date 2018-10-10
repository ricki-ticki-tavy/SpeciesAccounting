package org.ricki.catalog.web.page.styles.service;

public enum SystemStyleEnum {
  NONE("Обычный", "none"), GRAY("Серый", "gray"), LIGHT_GREEN("Светло зеленый", "lightGreen"), YELLOW("Желтый", "yellow"), ORANGE("Оранжевый", "orange"), RED("Красный", "red");

  private String name;
  private String caption;

  private SystemStyleEnum(String name, String caption) {
    this.name = name;
    this.caption = caption;
  }

  public String getName() {
    return name;
  }

  public String getCaption() {
    return caption;
  }
}
