package org.ricki.catalog.web;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/", method = {RequestMethod.GET, RequestMethod.POST})
public class Idp {
  @RequestMapping(method = RequestMethod.GET)
  String readBookmarks() {
    return "sdasdaasd";
  }
}
