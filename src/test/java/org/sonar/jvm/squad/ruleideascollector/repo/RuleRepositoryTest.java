package org.sonar.jvm.squad.ruleideascollector.repo;

import java.util.List;
import java.util.Set;
import java.util.stream.Stream;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.sonar.jvm.squad.ruleideascollector.model.Rule;
import org.sonar.jvm.squad.ruleideascollector.model.Status;
import org.sonar.jvm.squad.ruleideascollector.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.testcontainers.junit.jupiter.Testcontainers;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@Testcontainers
class RuleRepositoryTest extends ContainerBase {

  private static final Rule RULE_1 = Rule.builder()
    .id("rule_id_1")
    .title("Rule S1")
    .creator(User.builder().name("User_1").build())
    .status(Status.PROPOSAL)
    .build();

  private static final Rule RULE_2 = Rule.builder()
    .id("rule_id_2")
    .title("Rule S2")
    .creator(User.builder().name("User_1").build())
    .languages(Set.of("Java"))
    .tags(new String[]{ "obsolete", "spring" })
    .isSonarWay(true)
    .status(Status.ACCEPTED)
    .build();

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