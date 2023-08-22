package org.sonar.jvm.squad.ruleideascollector.repo;

import org.springframework.data.repository.Repository;
import org.sonar.jvm.squad.ruleideascollector.model.Rule;

public interface RuleRepository extends Repository<Rule, String> {}
