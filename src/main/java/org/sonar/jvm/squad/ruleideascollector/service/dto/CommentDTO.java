package org.sonar.jvm.squad.ruleideascollector.service.dto;

import lombok.AllArgsConstructor;
import lombok.Value;

@Value
@AllArgsConstructor
public class CommentDTO {

   String id;
   UserDTO creator;
   String text;
}
