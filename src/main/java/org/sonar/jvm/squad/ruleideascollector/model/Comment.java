package org.sonar.jvm.squad.ruleideascollector.model;

import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.SuperBuilder;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Builder
@Document
// TODO: inherit from BaseEntity
public class Comment {
  @Id
  public String id;
  private User creator;
  private String text;
}
