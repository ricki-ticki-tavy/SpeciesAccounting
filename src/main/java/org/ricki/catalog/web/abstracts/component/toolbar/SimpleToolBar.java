package org.ricki.catalog.web.abstracts.component.toolbar;

import com.vaadin.ui.Button;
import com.vaadin.ui.HorizontalLayout;

public class SimpleToolBar extends HorizontalLayout {
  public static final String SIMPLE_TOOLBAR_STYLE_NAME = "base_tool_bar";

  public SimpleToolBar() {
    super();
    addStyleName(SIMPLE_TOOLBAR_STYLE_NAME);
  }

  public Button createAndAddButton(String hint, String styleName, Button.ClickListener listener) {
    Button button = new Button();
    button.addStyleName(styleName);
    addComponent(button);
    if (listener != null) {
      button.addClickListener(listener);
    }
    return button;
  }
}
