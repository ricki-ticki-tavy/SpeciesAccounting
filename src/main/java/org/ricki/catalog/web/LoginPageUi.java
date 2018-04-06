package org.ricki.catalog.web;

import com.vaadin.server.Page;
import com.vaadin.server.VaadinRequest;
import com.vaadin.server.VaadinService;
import com.vaadin.spring.annotation.SpringUI;
import com.vaadin.spring.annotation.SpringViewDisplay;
import com.vaadin.ui.*;
import org.ricki.catalog.system.openid.UserTokenHolder;
import org.ricki.catalog.system.openid.UserTokenInfo;
import org.ricki.catalog.system.openid.server.AuthCodeRequestStruct;
import org.springframework.beans.factory.BeanFactory;

import javax.inject.Inject;

@SpringUI(path = "/login")
@SpringViewDisplay
public class LoginPageUi extends UI {

  public static final String BACK_URL = "backUrl";
  public static final String TOKEN = "token";

  UserTokenInfo userTokenInfo;
  AuthCodeRequestStruct authRequest = null;

  TextField loginField;
  PasswordField passwordField;

  @Inject
  BeanFactory beanFactory;

  @Inject
  UserTokenHolder userTokenHolder;

  private VaadinService vaadinService;

  private void addStyles() {
    Page.getCurrent().getStyles().add(".myLoginForm { margin: 10 !important; padding: 0 !important; background-color: grey}");
  }

  private String makeToken(UserTokenInfo userTokenInfo) {
    StringBuilder sb = new StringBuilder();

    return sb.toString();
  }
  //---------------------------------------------------------------------------------------

  @Override
  protected void init(VaadinRequest request) {

//    VaadinSession.getCurrent().addRequestHandler((RequestHandler) (session, request1, response) -> {
//      if (backUrl != null && request.getPathInfo().startsWith(backUrl)) {
//        response.setHeader(TOKEN, userTokenInfo.token);
//        response.setHeader(TOKEN, userTokenInfo.token);
//      }
//      return false;
//    });

    authRequest = AuthCodeRequestStruct.fromRequest(request);

    addStyles();
    vaadinService = request.getService();
    GridLayout form = new GridLayout(20, 8);
    form.setWidth(600, Unit.PIXELS);
    form.setHeight(200, Unit.PIXELS);
    form.addStyleName("myLoginForm");

    form.addComponent(new Label("Пользователь"), 1, 2, 7, 2);
    form.addComponent(new Label("Пароль"), 1, 4, 7, 4);
    loginField = new TextField();
    loginField.setWidth("100%");
    form.addComponent(loginField, 8, 2, 18, 2);
    passwordField = new PasswordField();
    passwordField.setWidth("100%");
    form.addComponent(passwordField, 8, 4, 18, 4);

    Button loginButton = new Button("Вход", event -> {
      String login = loginField.getValue();
      String password = passwordField.getValue();

      userTokenInfo = userTokenHolder.authenticate("THIS", login, password);
      if (userTokenInfo == null) {

      } else {
        if ("code".equals(authRequest.response_type)) {
          // Соберем ответ с кодом авторизации
          getCurrent().getPage().setLocation(authRequest.redirect_uri);
        }
      }
    });

    form.addComponent(loginButton, 8, 6, 12, 6);

    GridLayout uiLayout = new GridLayout(3, 3);
    uiLayout.setSizeFull();
    uiLayout.addComponent(new HorizontalLayout(), 0, 0, 2, 0);
    uiLayout.addComponent(new HorizontalLayout(), 0, 1, 0, 1);
    uiLayout.addComponent(form, 1, 1, 1, 1);
    uiLayout.addComponent(new HorizontalLayout(), 2, 1, 2, 1);
    uiLayout.addComponent(new HorizontalLayout(), 0, 2, 2, 2);


    setContent(uiLayout);
  }
}
