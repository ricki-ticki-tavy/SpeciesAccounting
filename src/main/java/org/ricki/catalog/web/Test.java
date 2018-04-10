package org.ricki.catalog.web;

import org.ricki.catalog.system.openid.UserTokenHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.inject.Inject;

@RestController
@RequestMapping("/login/marker")
public class Test {

  @Inject
  UserTokenHolder userTokenHolder;

  @PostMapping
  public String postRequest() {
    userTokenHolder.toString();
    return "sdsdsd";
  }

}

/*


 КО

 Id, Name
 --------------------

 photo

 id, name, comment, alt, data
 --------------------------------

 box

 id, name, size,






 */