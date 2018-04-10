package org.ricki.catalog.web.page.species.types;

import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.ui.Layout;
import com.vaadin.ui.VerticalLayout;
import org.ricki.catalog.entity.SpecieType;
import org.ricki.catalog.service.SpecieTypeService;
import org.ricki.catalog.web.abstracts.form.BaseForm;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;

import javax.inject.Inject;
import javax.inject.Named;

/**
 * Стили для вывода разных ячеек
 */
@Named
@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class SpecieTypeView extends BaseForm {

  @Inject
  SpecieTypeService specieTypeService;

  SpecieTypeListGrid grid;

  @Override
  public String getPageId() {
    return "specieTypes";
  }

  @Override
  public String getPageCaption() {
    return "Виды и условия содержания";
  }

  @Override
  public Layout buildContent() {
    VerticalLayout content = new VerticalLayout();
    content.setSizeFull();
    setCaption(getPageCaption());

    grid = new SpecieTypeListGrid();
    grid.initGrid(SpecieType.class);
    grid.setSizeFull();
    content.addComponent(grid);

    return content;
  }

  @Override
  public void onOpen(ViewChangeListener.ViewChangeEvent event) {
    grid.setItems(specieTypeService.getList());
  }

}
