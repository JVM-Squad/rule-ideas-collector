package org.sonar.jvm.squad.ruleideascollector.model;

import java.util.Set;
import lombok.Builder;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Builder
@Document
public class Rule {

  @Id
  public String id;

  public long creationTimestamp;
  public long modifiedTimestamp;
  private User creator;

  private String title;
  private String description;

  private Set<String> languages;
  private String[] tags;
  private Status status;

  private Boolean isSonarWay;

  private Comment[] comments;
}
