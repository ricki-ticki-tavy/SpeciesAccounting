package org.ricki.catalog.web.page.styles.form;

import org.ricki.catalog.web.abstracts.component.grid.MetadataGrid;
import org.ricki.catalog.web.abstracts.form.element.annotations.field.grid.ColumnInfo;
import org.ricki.catalog.web.abstracts.form.element.annotations.field.grid.GridMetadata;

@GridMetadata(gridUniqueId = "stylesList",
        columns = {@ColumnInfo(columnCaption = "Название", fieldName = "name", width = 200)
        , @ColumnInfo(columnCaption = "Системное имя", fieldName = "systemName", width = 75)
        , @ColumnInfo(columnCaption = "Примечание", fieldName = "comment", width = 400)
        , @ColumnInfo(columnCaption = "Sys", fieldName = "system", width = 75)
})
public class StyleListGrid<UserWebStyle> extends MetadataGrid<UserWebStyle> {
}
