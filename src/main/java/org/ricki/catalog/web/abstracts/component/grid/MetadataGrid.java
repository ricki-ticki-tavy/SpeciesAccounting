package org.ricki.catalog.web.abstracts.component.grid;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.ricki.catalog.entity.abstracts.BaseEntity;
import org.ricki.catalog.service.SystemSettingService;
import org.ricki.catalog.web.abstracts.form.element.annotations.field.grid.GridMetadata;
import org.springframework.util.StringUtils;
import org.springframework.web.context.support.SpringBeanAutowiringSupport;

import javax.inject.Inject;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

public abstract class MetadataGrid<T> extends com.vaadin.ui.Grid<T> {

  private static final String COLUMNS_PARAM_NAME = "userColumnParams";
  Class<? extends BaseEntity> anClass;
  protected List<T> items = null;

  private Gson json = new GsonBuilder().create();
  private boolean skipReorderEvent = false;

  protected String gridUniqueId;

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

  public String getGridUniqueId() {
    return gridUniqueId;
  }

  public void setGridUniqueId(String gridUniqueId) {
    this.gridUniqueId = gridUniqueId;
  }

  public void initGrid(Class<? extends BaseEntity> entityClass) {
    initGrid(entityClass, null);
  }

  @Override
  public void setItems(Collection<T> items) {
    if (items instanceof Collection) {
      this.items = new ArrayList<T>(items);
    } else {
      throw new RuntimeException("bad collection");
    }
    super.setItems(items);
  }

  public List<T> getItems() {
    return items;
  }

  public void initGrid(Class<? extends BaseEntity> entityClass, GridMetadata gridMetadata) {
    if (settingService == null) {
      SpringBeanAutowiringSupport.processInjectionBasedOnCurrentContext(this);
    }

    String userColumnParams = settingService.loadParameter(getGridUniqueId(), COLUMNS_PARAM_NAME, null);
    ColumnParamInfo[] columnParamInfos = null;
    if (userColumnParams != null) {
      columnParamInfos = json.fromJson(userColumnParams, ColumnParamInfo[].class);
    }

    setBeanType((Class<T>) entityClass);
    removeAllColumns();
    this.anClass = entityClass;

    if (gridMetadata == null) {
      gridMetadata = this.getClass().getDeclaredAnnotation(GridMetadata.class);
    }

    if (gridMetadata != null) {
      skipReorderEvent = true;
      if (!StringUtils.isEmpty(gridMetadata.gridUniqueId())) {
        setGridUniqueId(gridMetadata.gridUniqueId());
      }
      final String[] order = new String[gridMetadata.columns().length];
      final int[] cnt = {0};
      Arrays.stream(gridMetadata.columns()).forEach(info -> {
        addColumn(info.fieldName()).setCaption(info.columnCaption()).setWidth(info.width());
        order[cnt[0]++] = info.fieldName();
      });
      if (columnParamInfos != null) {
        cnt[0] = 0;
        Arrays.stream(columnParamInfos).forEach(colParamInfo -> {
          order[cnt[0]++] = colParamInfo.name;
          getColumn(colParamInfo.name).setWidth(colParamInfo.width);
        });
      }
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
