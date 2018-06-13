package org.ricki.catalog.web.page.styles;

import org.ricki.catalog.web.abstracts.component.grid.MetadataGrid;
import org.ricki.catalog.web.abstracts.form.list.ColumnInfo;
import org.ricki.catalog.web.abstracts.form.list.GridMetadata;

@GridMetadata(columns = {@ColumnInfo(columnCaption = "Название", fieldName = "name", width = 200)
        , @ColumnInfo(columnCaption = "Системное имя", fieldName = "systemName", width = 75)
        , @ColumnInfo(columnCaption = "Примечание", fieldName = "comment", width = 400)
        , @ColumnInfo(columnCaption = "Sys", fieldName = "system", width = 75)
})
public class StyleListGrid<UserWebStyle> extends MetadataGrid<UserWebStyle> {
  @Override
  public String getGridUniqueId() {
    return "stylesList";
  }
}
