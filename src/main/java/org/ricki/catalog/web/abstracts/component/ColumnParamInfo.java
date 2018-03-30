package org.ricki.catalog.web.abstracts.component;

class ColumnParamInfo {
  public String name;
  public int width;

  public ColumnParamInfo() {

  }

  public ColumnParamInfo(String name, int width) {
    this.name = name;
    this.width = width;
  }
}
