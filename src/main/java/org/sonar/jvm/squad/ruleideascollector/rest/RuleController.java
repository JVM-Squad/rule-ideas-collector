package org.sonar.jvm.squad.ruleideascollector.rest;

import org.sonar.jvm.squad.ruleideascollector.repo.RuleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class RuleController {

  @Autowired
  private RuleRepository repository;

  @GetMapping("/")
  public String index() {
    return "Greetings from Spring Boot!";
  }

}
