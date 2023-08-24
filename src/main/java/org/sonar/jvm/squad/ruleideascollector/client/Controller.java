package org.sonar.jvm.squad.ruleideascollector.client;

import java.util.Arrays;
import org.sonar.jvm.squad.ruleideascollector.controller.RuleController;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@org.springframework.stereotype.Controller
public class Controller {

  private RuleController ruleController;

  public Controller(RuleController ruleController) {
    this.ruleController = ruleController;
  }


  @GetMapping("/")
  public String showRuleList(Model model) {
    model.addAttribute("rules", ruleController.findAllRules(null, null));
    return "index";
  }
}
