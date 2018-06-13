package org.ricki.catalog.web.abstracts.form.element;

import com.vaadin.ui.GridLayout;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.Window;

import javax.inject.Named;
import javax.transaction.Transactional;

@Transactional
@Named
public class BaseEditForm extends Window {

  public static final String BASE_WINDOW_STYLE = "base_window_form_style";
  public static final String BASE_WINDOW_CAPTION_STYLE = "base_window_form_caption_style";

  private GridLayout rootContent;

  private Label captionLabel;

  public BaseEditForm() {
    setWidth(90, Unit.PERCENTAGE);
    setHeight(70, Unit.PERCENTAGE);
    captionLabel = new Label(" Caption");
    rootContent = new GridLayout(1, 100);
    rootContent.setSizeFull();
    setContent(rootContent);

    captionLabel.addStyleName(BASE_WINDOW_CAPTION_STYLE);
    captionLabel.setHeight(38, Unit.PIXELS);

    HorizontalLayout captionLayout = new HorizontalLayout();
    captionLayout.setWidth(100, Unit.PERCENTAGE);
    captionLayout.setHeight(32, Unit.PIXELS);
    captionLayout.addComponent(captionLabel);

    rootContent.addComponent(captionLayout, 0, 0, 0, 0);
    rootContent.addStyleName(BASE_WINDOW_STYLE);

    setResizable(false);
    setClosable(false);
    setSizeFull();

    setModal(true);
  }

  public GridLayout getRootContent() {
    return rootContent;
  }

  public void setWindowCaption(String caption) {
    captionLabel.setValue(caption);
  }
}
