package org.ricki.catalog.web.page.staff;

import org.ricki.catalog.web.abstracts.component.grid.MetadataGrid;
import org.ricki.catalog.web.abstracts.form.element.annotations.field.grid.ColumnInfo;
import org.ricki.catalog.web.abstracts.form.element.annotations.field.grid.GridMetadata;

@GridMetadata(columns = {@ColumnInfo(columnCaption = "Название", fieldName = "name", width = 200)
        , @ColumnInfo(columnCaption = "В наличии", fieldName = "present", width = 80)
        , @ColumnInfo(columnCaption = "Опубликована", fieldName = "published", width = 80)
        , @ColumnInfo(columnCaption = "Продается", fieldName = "forSale", width = 80)
        , @ColumnInfo(columnCaption = "Цена", fieldName = "requiredPrice", width = 120)
        , @ColumnInfo(columnCaption = "Продано за", fieldName = "payedPrice", width = 120)
        , @ColumnInfo(columnCaption = "Примечание", fieldName = "comment", width = 400)
        , @ColumnInfo(columnCaption = "Акт", fieldName = "active", width = 80)
})
public class StaffListGrid<Staff> extends MetadataGrid<Staff> {
  @Override
  public String getGridUniqueId() {
    return "staffsList";
  }
}
