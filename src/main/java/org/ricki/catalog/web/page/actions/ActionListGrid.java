package org.ricki.catalog.web.page.actions;

import org.ricki.catalog.entity.metadata.annotation.ColumnInfo;
import org.ricki.catalog.entity.metadata.annotation.GridMetadata;
import org.ricki.catalog.web.abstracts.component.grid.MetadataGrid;

@GridMetadata(columns = {@ColumnInfo(columnCaption = "Название", fieldName = "name", width = 200)
        , @ColumnInfo(columnCaption = "Стиль не вып.", fieldName = "planedStyle", width = 75)
        , @ColumnInfo(columnCaption = "Применимо к", fieldName = "actionRecipient", width = 200)
        , @ColumnInfo(columnCaption = "Примечание", fieldName = "comment", width = 400)
        , @ColumnInfo(columnCaption = "Sys", fieldName = "system", width = 75)
})
public class ActionListGrid<AnAction> extends MetadataGrid<AnAction> {
  @Override
  public String getGridUniqueId() {
    return "actionList";
  }
}
