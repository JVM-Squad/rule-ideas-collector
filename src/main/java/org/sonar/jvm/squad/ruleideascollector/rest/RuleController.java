package org.sonar.jvm.squad.ruleideascollector.rest;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Optional;
import org.sonar.jvm.squad.ruleideascollector.model.Rule;
import org.sonar.jvm.squad.ruleideascollector.repo.RuleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RuleController {

  @Autowired
  // TODO: pass via constructor
  private RuleRepository repository;

  @GetMapping("/rules")
  @ResponseBody
  // TODO: implement actual paging. Parameters (offst, size) can be renamed.
  public List<Rule> findAllRules(
    @RequestParam(required = false) Integer offset,
    @RequestParam(required = false) Integer size
  ) {
    return repository.findAll();
  }

  @GetMapping("/rules/{id}")
  @ResponseBody
  public Optional<Rule> findRuleById(@PathVariable String id) {
    return repository.findById(id);
  }

  @PutMapping("/rules")
  public void modifyRule(
    @RequestBody Rule rule,
    HttpServletResponse response
  ) {
    if (findRuleById(rule.id).isEmpty()) {
      response.setStatus( HttpServletResponse.SC_BAD_REQUEST);
    } else {
      repository.save(rule);
    }
  }

  @PostMapping("/rules")
  @ResponseBody
  public String createRule(
    @RequestBody Rule rule,
    HttpServletResponse response
  ) {
    if (rule.id != "NULL") {
      response.setStatus( HttpServletResponse.SC_BAD_REQUEST);
    } else {
      rule = repository.save(rule);
    }
    return rule.id;
  }
}
