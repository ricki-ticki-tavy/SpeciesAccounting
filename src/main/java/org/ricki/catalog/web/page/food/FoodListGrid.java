package org.ricki.catalog.web.page.food;

import org.ricki.catalog.entity.metadata.annotation.ColumnInfo;
import org.ricki.catalog.entity.metadata.annotation.GridMetadata;
import org.ricki.catalog.web.abstracts.component.grid.MetadataGrid;

@GridMetadata(columns = {@ColumnInfo(columnCaption = "Название", fieldName = "name", width = 200)
        , @ColumnInfo(columnCaption = "В наличии", fieldName = "present", width = 80)
        , @ColumnInfo(columnCaption = "Опубликована", fieldName = "published", width = 80)
        , @ColumnInfo(columnCaption = "Продается", fieldName = "forSale", width = 80)
        , @ColumnInfo(columnCaption = "Цена", fieldName = "requiredPrice", width = 120)
        , @ColumnInfo(columnCaption = "Продано за", fieldName = "payedPrice", width = 120)
        , @ColumnInfo(columnCaption = "Примечание", fieldName = "comment", width = 400)
        , @ColumnInfo(columnCaption = "Акт", fieldName = "active", width = 70)
        , @ColumnInfo(columnCaption = "Остаток", fieldName = "quantity", width = 75)
})
public class FoodListGrid<Food> extends MetadataGrid<Food> {
  @Override
  public String getGridUniqueId() {
    return "foodList";
  }
}
