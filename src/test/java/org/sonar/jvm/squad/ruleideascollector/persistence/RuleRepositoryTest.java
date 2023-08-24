package org.sonar.jvm.squad.ruleideascollector.persistence;

import java.util.List;
import java.util.stream.Stream;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.sonar.jvm.squad.ruleideascollector.persistence.model.Rule;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.testcontainers.junit.jupiter.Testcontainers;

import static org.assertj.core.api.Assertions.assertThat;
import static org.sonar.jvm.squad.ruleideascollector.TestUtils.RULE_1;
import static org.sonar.jvm.squad.ruleideascollector.TestUtils.RULE_2;

@SpringBootTest
@Testcontainers
class RuleRepositoryTest extends ContainerBase {

  @Autowired
  RuleRepository ruleRepository;

  @ParameterizedTest
  @MethodSource("provideRulesToSave")
  @DisplayName("given a list of Rules to save when save the Rules with RuleRepository then the Rules are saved")
  void test_saveAndFindAllSingleRule(List<Rule> rules) {
    for(Rule rule : rules) ruleRepository.save(rule);
    assertThat(ruleRepository.findAll()).containsExactlyElementsOf(rules);
  }

  private static Stream<Arguments> provideRulesToSave() {
    return Stream.of(
      Arguments.of(List.of()),
      Arguments.of(List.of(RULE_1)),
      Arguments.of(List.of(RULE_1 , RULE_2))
    );
  }
}