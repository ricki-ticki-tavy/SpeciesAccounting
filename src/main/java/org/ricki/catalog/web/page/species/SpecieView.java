package org.ricki.catalog.web.page.species;

import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.ui.Layout;
import com.vaadin.ui.VerticalLayout;
import org.ricki.catalog.entity.Specie;
import org.ricki.catalog.service.SpecieService;
import org.ricki.catalog.web.abstracts.form.list.BaseForm;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;

import javax.inject.Inject;
import javax.inject.Named;

/**
 * Особи
 */
@Named
@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class SpecieView extends BaseForm {

  @Inject
  private SpecieService specieService;

  private SpecieListGrid grid;

  @Override
  public String getPageId() {
    return "species";
  }

  @Override
  public String getPageCaption() {
    return "Особи";
  }

  @Override
  public Layout buildContent() {
    VerticalLayout content = new VerticalLayout();
    content.setSizeFull();
    setCaption(getPageCaption());

    grid = new SpecieListGrid();
    grid.initGrid(Specie.class);
    grid.setSizeFull();
    content.addComponent(grid);

    return content;
  }

  @Override
  public void onOpen(ViewChangeListener.ViewChangeEvent event) {
    grid.setItems(specieService.getList());

  }

}
