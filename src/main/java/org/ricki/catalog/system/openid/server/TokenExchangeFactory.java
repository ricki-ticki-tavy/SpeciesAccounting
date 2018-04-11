package org.ricki.catalog.system.openid.server;

import org.ricki.catalog.system.openid.UserTokenHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.inject.Inject;

import static org.ricki.catalog.system.openid.server.AuthCodeRequestStruct.CLIENT_ID_PARAM_NAME;

@RestController
@RequestMapping("/factory")
public class TokenExchangeFactory {

  @Inject
  UserTokenHolder userTokenHolder;

//  @PostMapping
//  public String postRequest() {
//    return getRequest();
//  }

  @GetMapping
  public String getRequest(@RequestParam(CLIENT_ID_PARAM_NAME) String clientId) {
    if (clientId == null) {

    }
    userTokenHolder.toString();
    return "sdsdsd";
  }

}
