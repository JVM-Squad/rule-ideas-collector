package org.sonar.jvm.squad.ruleideascollector.repo;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.sonar.jvm.squad.ruleideascollector.model.Rule;

public interface RuleRepository extends MongoRepository<Rule, String> {}
