package org.sonar.jvm.squad.ruleideascollector.dto;

public record CommentDTO (
   String id,
   UserDTO creator,
   String text
) {
}
