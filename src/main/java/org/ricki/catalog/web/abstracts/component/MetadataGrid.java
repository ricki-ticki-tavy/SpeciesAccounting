package org.ricki.catalog.web.abstracts.component;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.ricki.catalog.entity.abstracts.BaseEntity;
import org.ricki.catalog.entity.metadata.annotation.GridMetadata;
import org.ricki.catalog.service.SystemSettingService;
import org.springframework.web.context.support.SpringBeanAutowiringSupport;

import javax.inject.Inject;
import java.util.Arrays;

public abstract class MetadataGrid<T> extends com.vaadin.ui.Grid<T> {

  private static final String COLUMNS_PARAM_NAME = "userColumnParams";
  Class<? extends BaseEntity> anClass;

  private Gson json = new GsonBuilder().create();
  private boolean skipReorderEvent = false;

  @Inject
  SystemSettingService settingService;

  private void saveColumnsOrder(Event event) {
    if (!skipReorderEvent) {
      MetadataGrid grid = (MetadataGrid) event.getSource();
      ColumnParamInfo[] columnParamInfos = new ColumnParamInfo[grid.getColumns().size()];
      int[] cnt = new int[]{0};
      grid.getColumns().stream().forEach(column -> {
        columnParamInfos[cnt[0]++] = new ColumnParamInfo(((Column) column).getId(), (int) ((Column) column).getWidth());
      });
      String jsonData = json.toJson(columnParamInfos);
      settingService.saveParameter(getGridUniqueId(), COLUMNS_PARAM_NAME, jsonData);
    }
  }

  public abstract String getGridUniqueId();

  public void initGrid(Class<? extends BaseEntity> anClass) {
    if (settingService == null) {
      SpringBeanAutowiringSupport.processInjectionBasedOnCurrentContext(this);
    }

    String userColumnParams = settingService.loadParameter(getGridUniqueId(), COLUMNS_PARAM_NAME, null);
    ColumnParamInfo[] columnParamInfos = null;
    if (userColumnParams != null) {
      columnParamInfos = json.fromJson(userColumnParams, ColumnParamInfo[].class);
    }

    setBeanType((Class<T>) anClass);
    removeAllColumns();
    this.anClass = anClass;
    GridMetadata gridMetadata = this.getClass().getDeclaredAnnotation(GridMetadata.class);
    if (gridMetadata != null) {
      final String[] order = new String[gridMetadata.columns().length];
      final int[] cnt = {0};
      Arrays.stream(gridMetadata.columns()).forEach(info -> {
        Column col = addColumn(info.fieldName()).setCaption(info.columnCaption()).setWidth(info.width());
        order[cnt[0]++] = info.fieldName();
      });
      if (columnParamInfos != null) {
        cnt[0] = 0;
        Arrays.stream(columnParamInfos).forEach(colParamInfo -> {
          order[cnt[0]++] = colParamInfo.name;
          getColumn(colParamInfo.name).setWidth(colParamInfo.width);
        });
      }
      skipReorderEvent = true;
      setColumnOrder(order);
      skipReorderEvent = false;
      setSelectionMode(SelectionMode.SINGLE);
    }
  }

  public MetadataGrid() {
    setColumnReorderingAllowed(true);
    addColumnResizeListener(event -> saveColumnsOrder(event));
    addColumnReorderListener(event -> saveColumnsOrder(event));
  }
}
