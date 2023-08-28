package org.sonar.jvm.squad.ruleideascollector;

import java.util.Set;
import org.sonar.jvm.squad.ruleideascollector.persistence.model.Comment;
import org.sonar.jvm.squad.ruleideascollector.persistence.model.Rule;
import org.sonar.jvm.squad.ruleideascollector.persistence.model.Status;
import org.sonar.jvm.squad.ruleideascollector.persistence.model.User;
import org.sonar.jvm.squad.ruleideascollector.service.MapperUtils;
import org.sonar.jvm.squad.ruleideascollector.service.dto.RuleDTO;
import org.sonar.jvm.squad.ruleideascollector.service.dto.UserDTO;

public class TestUtils {

  public static final User USER_1 = new User("User_1", "User_1");
  public static final Rule RULE_WITH_INVALID_ID = Rule.builder()
    .id(null)
    .title("Rule S1")
    .creator(USER_1)
    .status(Status.PROPOSAL)
    .build();
  public static final Rule RULE_1 = Rule.builder()
    .id("rule_id_1")
    .title("Rule S1")
    .creator(USER_1)
    .status(Status.PROPOSAL)
    .build();
  public static final Rule RULE_2 = Rule.builder()
    .id("rule_id_2")
    .title("Rule S2")
    .creator(USER_1)
    .languages(Set.of("Java"))
    .tags(new String[]{ "obsolete", "spring" })
    .isSonarWay(true)
    .status(Status.ACCEPTED)
    .comments(new Comment[]{ new Comment("comment_1", USER_1, "comment") })
    .build();

  public static final UserDTO USER_DTO_1 = MapperUtils.fromModel(USER_1);
  public static final RuleDTO RULE_DTO_WITH_INVALID_ID = MapperUtils.fromModel(RULE_WITH_INVALID_ID);
  public static final RuleDTO RULE_DTO_1 = MapperUtils.fromModel(RULE_1);
  public static final RuleDTO RULE_DTO_2 = MapperUtils.fromModel(RULE_2);

  private TestUtils() {}
}
