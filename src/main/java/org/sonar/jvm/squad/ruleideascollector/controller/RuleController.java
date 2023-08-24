package org.sonar.jvm.squad.ruleideascollector.controller;

import jakarta.servlet.http.HttpServletResponse;
import java.util.List;
import org.sonar.jvm.squad.ruleideascollector.persistence.model.Rule;
import org.sonar.jvm.squad.ruleideascollector.persistence.RuleRepository;
import org.sonar.jvm.squad.ruleideascollector.service.RuleService;
import org.sonar.jvm.squad.ruleideascollector.service.dto.RuleDTO;
import org.sonar.jvm.squad.ruleideascollector.service.dto.RuleOverviewDTO;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RuleController {

  private final RuleService ruleService;

  public RuleController(RuleService ruleService) {
    this.ruleService = ruleService;
  }

  @GetMapping("/rules")
  // TODO: implement actual paging. Parameters (offst, size) can be renamed.
  public List<RuleDTO> findAllRules(
    @RequestParam(required = false) Integer offset,
    @RequestParam(required = false) Integer size) {

    return ruleService.getRules();
  }

  @GetMapping("/rules/overviews")
  // TODO: implement actual paging. Parameters (offst, size) can be renamed.
  public List<RuleOverviewDTO> findAllRuleOverviews(
    @RequestParam(required = false) Integer offset,
    @RequestParam(required = false) Integer size) {

    return ruleService.getRuleOverviews();
  }

  @GetMapping("/rules/{id}")
  public RuleDTO findRuleById(@PathVariable String id, HttpServletResponse response) {
    var rule = ruleService.getRule(id);
    if (rule.isEmpty()) {
      response.setStatus( HttpServletResponse.SC_NOT_FOUND);
      return null;
    } else {
      response.setStatus( HttpServletResponse.SC_OK);
      return rule.get();
    }
  }

  @PutMapping("/rules")
  public void modifyRule(@RequestBody RuleDTO rule, HttpServletResponse response) {
    var success = ruleService.modifyRule(rule);

    if (!success) {
      response.setStatus( HttpServletResponse.SC_BAD_REQUEST);
    }
  }

  @PostMapping("/rules")
  public String createRule(@RequestBody RuleDTO rule, HttpServletResponse response) {
    var id = ruleService.createRule(rule);

    if (id == null) {
      response.setStatus( HttpServletResponse.SC_BAD_REQUEST);
    }

    return id;
  }
}
