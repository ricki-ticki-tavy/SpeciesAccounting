package org.ricki.catalog.web.page.styles;

import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.ui.Layout;
import com.vaadin.ui.VerticalLayout;
import org.ricki.catalog.entity.UserWebStyle;
import org.ricki.catalog.service.StyleService;
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
public class StyleView extends BaseForm {

  @Inject
  StyleService styleService;

  StyleListGrid grid;

  @Override
  public String getPageId() {
    return "styles";
  }

  @Override
  public String getPageCaption() {
    return "Стили отображения";
  }

  @Override
  public Layout buildContent() {
    VerticalLayout content = new VerticalLayout();
    content.setSizeFull();
    setCaption(getPageCaption());

    grid = new StyleListGrid();
    grid.initGrid(UserWebStyle.class);
    grid.setSizeFull();
    content.addComponent(grid);

    return content;
  }

  @Override
  public void onOpen(ViewChangeListener.ViewChangeEvent event) {
    grid.setItems(styleService.getList());
  }

}
