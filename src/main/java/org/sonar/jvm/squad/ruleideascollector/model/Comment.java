package org.sonar.jvm.squad.ruleideascollector.model;

import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.SuperBuilder;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Builder
@Document
public class Comment {

  private User creator;
  private String text;
}
