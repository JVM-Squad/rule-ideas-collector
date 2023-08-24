package org.sonar.jvm.squad.ruleideascollector.service;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import org.sonar.jvm.squad.ruleideascollector.service.dto.RuleDTO;
import org.sonar.jvm.squad.ruleideascollector.service.dto.RuleOverviewDTO;

public interface RuleService {

  List<RuleDTO> getRules();

  List<RuleOverviewDTO> getRuleOverviews();

  Optional<RuleDTO> getRule(String id);

  String createRule(RuleDTO ruleDTO);

  boolean modifyRule(RuleDTO ruleDTO);
}
