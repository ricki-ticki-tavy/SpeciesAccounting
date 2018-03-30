package org.ricki.catalog.web;

import org.ricki.catalog.service.FoodService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.inject.Inject;

@RestController
@RequestMapping("/main")
public class Test {

  @Inject
  FoodService foodService;

  @GetMapping
  public String request() {
    foodService.getAllFoods();
    return "sdfsdfsfdsdf";
  }

  @PostMapping
  public String postRequest() {
    return request();
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