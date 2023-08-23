package org.sonar.jvm.squad.ruleideascollector.model;

import lombok.Builder;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Builder
@Document
// TODO: inherit from BaseEntity
public class User {
  @Id
  public String id;
  private String name;
}
