package org.ricki.catalog.web.page.action.form;

import org.ricki.catalog.web.abstracts.component.grid.MetadataGrid;
import org.ricki.catalog.web.abstracts.form.element.annotations.field.grid.ColumnInfo;
import org.ricki.catalog.web.abstracts.form.element.annotations.field.grid.GridMetadata;

@GridMetadata(gridUniqueId = "actionList",
        columns = {@ColumnInfo(columnCaption = "Название", fieldName = "name", width = 200)
        , @ColumnInfo(columnCaption = "Стиль не вып.", fieldName = "planedStyle", width = 75)
        , @ColumnInfo(columnCaption = "Применимо к", fieldName = "actionRecipient", width = 200)
        , @ColumnInfo(columnCaption = "Примечание", fieldName = "comment", width = 400)
        , @ColumnInfo(columnCaption = "Sys", fieldName = "system", width = 75)
})
public class ActionListGrid<AnAction> extends MetadataGrid<AnAction> {
}
