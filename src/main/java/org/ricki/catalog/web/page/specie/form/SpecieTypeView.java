package org.ricki.catalog.web.page.specie.form;

import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.ui.Layout;
import com.vaadin.ui.VerticalLayout;
import org.ricki.catalog.web.abstracts.form.list.BaseForm;
import org.ricki.catalog.web.page.specie.entity.SpecieType;
import org.ricki.catalog.web.page.specie.service.SpecieClassService;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;

import javax.inject.Inject;
import javax.inject.Named;

/**
 * Види живности
 */
@Named
@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class SpecieTypeView extends BaseForm {

  @Inject
  SpecieClassService specieTypeService;

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
