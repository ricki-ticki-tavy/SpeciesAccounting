package org.ricki.catalog.web.page.actions.forms;

import org.ricki.catalog.web.abstracts.component.grid.MetadataGrid;
import org.ricki.catalog.web.abstracts.form.element.annotations.field.grid.ColumnInfo;
import org.ricki.catalog.web.abstracts.form.element.annotations.field.grid.GridMetadata;

@GridMetadata(gridUniqueId = "actionResultList",
        columns = {@ColumnInfo(columnCaption = "Название", fieldName = "name", width = 200)
                , @ColumnInfo(columnCaption = "Стиль отображения", fieldName = "planedStyle", width = 75)})
public class ActionResultListGrid<ActionResult> extends MetadataGrid<ActionResult> {
}
