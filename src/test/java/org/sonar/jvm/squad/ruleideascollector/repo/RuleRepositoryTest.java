package org.sonar.jvm.squad.ruleideascollector.repo;

import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.sonar.jvm.squad.ruleideascollector.model.Rule;
import org.sonar.jvm.squad.ruleideascollector.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.testcontainers.junit.jupiter.Testcontainers;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@Testcontainers
class RuleRepositoryTest extends ContainerBase {


  @Autowired
  RuleRepository ruleRepository;

  @DisplayName("given a Rule to save"
    + " when save the Rule using"
    + " then the Rule is saved")
  @Test
  void test() {
    Rule rule = Rule.builder()
      .id("rule_id_1")
      .title("Rule S1")
      .creator(User.builder().name("User_1").build())
      .build();

    ruleRepository.save(rule);
    List<Rule> rules = ruleRepository.findAll();
    assertThat(rules).containsExactly(rule);
  }

}