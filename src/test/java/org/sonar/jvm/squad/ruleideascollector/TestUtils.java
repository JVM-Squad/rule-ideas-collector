package org.sonar.jvm.squad.ruleideascollector;

import java.util.Set;
import org.sonar.jvm.squad.ruleideascollector.model.Rule;
import org.sonar.jvm.squad.ruleideascollector.model.Status;
import org.sonar.jvm.squad.ruleideascollector.model.User;

public class TestUtils {
  public static final Rule RULE_WITH_INVALID_ID = Rule.builder()
    .id(null)
    .title("Rule S1")
    .creator(User.builder().name("User_1").build())
    .status(Status.PROPOSAL)
    .build();

  public static final Rule RULE_1 = Rule.builder()
    .id("rule_id_1")
    .title("Rule S1")
    .creator(User.builder().name("User_1").build())
    .status(Status.PROPOSAL)
    .build();

  public static final Rule RULE_2 = Rule.builder()
    .id("rule_id_2")
    .title("Rule S2")
    .creator(User.builder().name("User_1").build())
    .languages(Set.of("Java"))
    .tags(new String[]{ "obsolete", "spring" })
    .isSonarWay(true)
    .status(Status.ACCEPTED)
    .build();


  private TestUtils() {}
}
