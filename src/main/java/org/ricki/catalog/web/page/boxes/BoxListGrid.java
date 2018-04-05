package org.ricki.catalog.web.page.boxes;

import org.ricki.catalog.entity.metadata.annotation.ColumnInfo;
import org.ricki.catalog.entity.metadata.annotation.GridMetadata;
import org.ricki.catalog.web.abstracts.component.MetadataGrid;

@GridMetadata(columns = {@ColumnInfo(columnCaption = "Название", fieldName = "name", width = 200)
        , @ColumnInfo(columnCaption = "В наличии", fieldName = "present", width = 50)
        , @ColumnInfo(columnCaption = "Опубликована", fieldName = "published", width = 50)
        , @ColumnInfo(columnCaption = "Продается", fieldName = "forSale", width = 50)
        , @ColumnInfo(columnCaption = "Цена", fieldName = "requiredPrice", width = 80)
        , @ColumnInfo(columnCaption = "Продано за", fieldName = "payedPrice", width = 80)
        , @ColumnInfo(columnCaption = "Примечание", fieldName = "comment", width = 400)
        , @ColumnInfo(columnCaption = "Акт", fieldName = "active", width = 50)
        , @ColumnInfo(columnCaption = "Параметры", fieldName = "info", width = 75)
})
public class BoxListGrid<Box> extends MetadataGrid<Box> {
  @Override
  public String getGridUniqueId() {
    return "boxesList";
  }
}
