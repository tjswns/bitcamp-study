package bitcamp.myapp.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
public class HomeController {

  {
    System.out.println("HomeContriller 생성됨!");
  }
  @GetMapping("/")
  public String home() throws Exception {
    return "/WEB-INF/jsp/index.jsp";
  }
}
