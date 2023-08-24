package org.sonar.jvm.squad.ruleideascollector.service.dto;

import java.time.LocalDateTime;
import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class RuleDTO {

   String id;

   RuleOverviewDTO ruleOverviewDTO;

   UserDTO creator;
   LocalDateTime creationTimestamp;
   LocalDateTime modifiedTimestamp;

   String description;
   CommentDTO[] comments;
}
