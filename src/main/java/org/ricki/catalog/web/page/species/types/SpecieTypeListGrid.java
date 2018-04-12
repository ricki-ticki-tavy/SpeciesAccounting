package org.ricki.catalog.web.page.species.types;

import org.ricki.catalog.entity.metadata.annotation.ColumnInfo;
import org.ricki.catalog.entity.metadata.annotation.GridMetadata;
import org.ricki.catalog.web.abstracts.component.grid.MetadataGrid;

@GridMetadata(columns = {@ColumnInfo(columnCaption = "Название", fieldName = "name", width = 400)
        , @ColumnInfo(columnCaption = "Вид", fieldName = "specieClass", width = 120)
        , @ColumnInfo(columnCaption = "Тдн min", fieldName = "temprDayMin", width = 90)
        , @ColumnInfo(columnCaption = "Тдн max", fieldName = "temprDayMax", width = 90)
        , @ColumnInfo(columnCaption = "Тнч min", fieldName = "temprNightMin", width = 90)
        , @ColumnInfo(columnCaption = "Тнч max", fieldName = "temprNightMax", width = 90)
        , @ColumnInfo(columnCaption = "Вл min", fieldName = "humidityMin", width = 90)
        , @ColumnInfo(columnCaption = "Вл max", fieldName = "humidityMax", width = 90)
        , @ColumnInfo(columnCaption = "Агрессивность", fieldName = "aggressionLevel", width = 140)
        , @ColumnInfo(columnCaption = "Яд", fieldName = "poisonLevel", width = 130)
})
public class SpecieTypeListGrid<SpecieType> extends MetadataGrid<SpecieType> {
  @Override
  public String getGridUniqueId() {
    return "specieTypesList";
  }
}
