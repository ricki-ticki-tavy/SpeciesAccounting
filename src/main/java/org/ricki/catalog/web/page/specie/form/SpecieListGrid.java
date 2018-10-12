package org.ricki.catalog.web.page.specie.form;

import org.ricki.catalog.web.abstracts.component.grid.MetadataGrid;
import org.ricki.catalog.web.abstracts.form.element.annotations.field.grid.ColumnInfo;
import org.ricki.catalog.web.abstracts.form.element.annotations.field.grid.GridMetadata;

@GridMetadata(columns = {
        @ColumnInfo(columnCaption = "Вид", fieldName = "specieType", width = 370)
        , @ColumnInfo(columnCaption = "Название", fieldName = "name", width = 120)
        , @ColumnInfo(columnCaption = "Част. корм.", fieldName = "feedInterval", width = 120)
        , @ColumnInfo(columnCaption = "Терр.", fieldName = "box", width = 70)
        , @ColumnInfo(columnCaption = "Пол", fieldName = "gender", width = 80)
        , @ColumnInfo(columnCaption = "В наличии", fieldName = "present", width = 120)
        , @ColumnInfo(columnCaption = "Опубликована", fieldName = "published", width = 135)
        , @ColumnInfo(columnCaption = "Продается", fieldName = "forSale", width = 120)
        , @ColumnInfo(columnCaption = "Цена", fieldName = "requiredPrice", width = 120)
        , @ColumnInfo(columnCaption = "Примечание", fieldName = "comment", width = 400)
        , @ColumnInfo(columnCaption = "Акт", fieldName = "active", width = 80)
})
public class SpecieListGrid<Specie> extends MetadataGrid<Specie> {
  @Override
  public String getGridUniqueId() {
    return "speciesList";
  }
}

