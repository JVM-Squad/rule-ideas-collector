package org.sonar.jvm.squad.ruleideascollector.rest;

import jakarta.servlet.http.HttpServletResponse;
import java.util.List;
import org.sonar.jvm.squad.ruleideascollector.model.Rule;
import org.sonar.jvm.squad.ruleideascollector.repo.RuleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RuleController {

  private RuleRepository repository;

  public RuleController(RuleRepository repository) {
    this.repository = repository;
  }

  //TODO: refactor to use DTO and a Service

  @GetMapping("/rules")
  // TODO: implement actual paging. Parameters (offst, size) can be renamed.
  public List<Rule> findAllRules(
    @RequestParam(required = false) Integer offset,
    @RequestParam(required = false) Integer size
  ) {
    return repository.findAll();
  }

  @GetMapping("/rules/{id}")
  public Rule findRuleById(
    @PathVariable String id,
    HttpServletResponse response
  ) {
    var rule = repository.findById(id);
    if (rule.isEmpty()) {
      response.setStatus( HttpServletResponse.SC_NOT_FOUND);
      return null;
    } else {
      response.setStatus( HttpServletResponse.SC_OK);
      return rule.get();
    }
  }

  @PutMapping("/rules")
  public void modifyRule(
    @RequestBody Rule rule,
    HttpServletResponse response
  ) {
    if (repository.findById(rule.id).isEmpty()) {
      response.setStatus( HttpServletResponse.SC_BAD_REQUEST);
    } else {
      repository.save(rule);
    }
  }

  @PostMapping("/rules")
  public String createRule(
    @RequestBody Rule rule,
    HttpServletResponse response
  ) {
    if (rule.id == null) {
      response.setStatus( HttpServletResponse.SC_BAD_REQUEST);
      return null;
    } else {
      rule = repository.save(rule);
      return rule.id;
    }
  }
}
