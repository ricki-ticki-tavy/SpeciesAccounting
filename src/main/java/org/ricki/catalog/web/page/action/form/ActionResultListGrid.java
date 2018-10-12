package org.ricki.catalog.web.page.action.form;

import org.ricki.catalog.web.abstracts.component.grid.MetadataGrid;
import org.ricki.catalog.web.abstracts.form.element.annotations.field.grid.ColumnInfo;
import org.ricki.catalog.web.abstracts.form.element.annotations.field.grid.GridMetadata;

@GridMetadata(gridUniqueId = "actionResultList",
        columns = {@ColumnInfo(columnCaption = "Название", fieldName = "name", width = 200)
                , @ColumnInfo(columnCaption = "Стиль отображения", fieldName = "style", width = 75)})
public class ActionResultListGrid<ActionResult> extends MetadataGrid<ActionResult> {
}
