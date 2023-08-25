package org.sonar.jvm.squad.ruleideascollector.service.dto;

public record CommentDTO (

   String id,
   UserDTO creator,
   String text
) {

}
