package org.sonar.jvm.squad.ruleideascollector.service;

import java.util.Arrays;
import org.sonar.jvm.squad.ruleideascollector.persistence.model.Comment;
import org.sonar.jvm.squad.ruleideascollector.persistence.model.Rule;
import org.sonar.jvm.squad.ruleideascollector.persistence.model.User;
import org.sonar.jvm.squad.ruleideascollector.service.dto.CommentDTO;
import org.sonar.jvm.squad.ruleideascollector.service.dto.RuleDTO;
import org.sonar.jvm.squad.ruleideascollector.service.dto.RuleOverviewDTO;
import org.sonar.jvm.squad.ruleideascollector.service.dto.UserDTO;

public class MapperUtils {

  private MapperUtils() {}

  public static RuleDTO fromModel(Rule rule) {
    return RuleDTO.builder()
      .id(rule.getId())
      .ruleOverviewDTO(RuleOverviewDTO.builder()
        .title(rule.getTitle())
        .tags(rule.getTags())
        .languages(rule.getLanguages())
        .isSonarWay(rule.getIsSonarWay())
        .status(rule.getStatus())
        .build())
      .creator(fromModel(rule.getCreator()))
      .creationTimestamp(rule.getCreationTimestamp())
      .modifiedTimestamp(rule.getModifiedTimestamp())
      .description(rule.getDescription())
      .comments(fromModel(rule.getComments()))
      .build();
  }

  public static CommentDTO[] fromModel(Comment[] comments) {
    if (comments == null) return null;
    return Arrays.stream(comments).map(MapperUtils::fromModel).toArray(CommentDTO[]::new);
  }

  public static CommentDTO fromModel(Comment comment) {
    return new CommentDTO(comment.getId(), fromModel(comment.getCreator()), comment.getText());
  }

  public static UserDTO fromModel(User user) {
    if (user != null) {
      return new UserDTO(user.getId(), user.getName());
    } else {
      return null;
    }
  }

  public static Rule fromDTO(RuleDTO ruleDTO) {
    return Rule.builder()
      .id(ruleDTO.id())
      .title(ruleDTO.ruleOverviewDTO().title())
      .tags(ruleDTO.ruleOverviewDTO().tags())
      .languages(ruleDTO.ruleOverviewDTO().languages())
      .isSonarWay(ruleDTO.ruleOverviewDTO().isSonarWay())
      .status(ruleDTO.ruleOverviewDTO().status())
      .creator(fromDTO(ruleDTO.creator()))
      .creationTimestamp(ruleDTO.creationTimestamp())
      .modifiedTimestamp(ruleDTO.modifiedTimestamp())
      .description(ruleDTO.description())
      .comments(fromDTO(ruleDTO.comments()))
      .build();
  }

  public static Comment[] fromDTO(CommentDTO[] commentDTOs) {
    if (commentDTOs == null) return null;

    return Arrays.stream(commentDTOs).map(MapperUtils::fromDTO).toArray(Comment[]::new);
  }

  public static Comment fromDTO(CommentDTO commentDTO) {
    return new Comment(commentDTO.getId(), fromDTO(commentDTO.getCreator()), commentDTO.getText());
  }

  public static User fromDTO(UserDTO userDTO) {
    if (userDTO != null) {
      return new User(userDTO.getId(), userDTO.getName());
    } else {
      return null;
    }
  }
}
