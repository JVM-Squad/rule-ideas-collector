package org.sonar.jvm.squad.ruleideascollector.service;

import java.util.List;
import java.util.Optional;
import org.sonar.jvm.squad.ruleideascollector.dto.RuleDTO;
import org.sonar.jvm.squad.ruleideascollector.dto.RuleOverviewDTO;

public interface RuleService {

  List<RuleDTO> getRules();

  List<RuleOverviewDTO> getRuleOverviews();

  Optional<RuleDTO> getRule(String id);

  String createRule(RuleDTO ruleDTO);

  boolean modifyRule(RuleDTO ruleDTO);
}
