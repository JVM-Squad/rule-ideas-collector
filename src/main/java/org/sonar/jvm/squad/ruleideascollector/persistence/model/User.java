package org.sonar.jvm.squad.ruleideascollector.persistence.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@AllArgsConstructor
@Document
public class User {
  @Id
  private String id;
  private String name;
}
