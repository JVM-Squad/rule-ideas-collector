package org.sonar.jvm.squad.ruleideascollector.persistence;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.sonar.jvm.squad.ruleideascollector.persistence.model.Rule;

public interface RuleRepository extends MongoRepository<Rule, String> {
}
