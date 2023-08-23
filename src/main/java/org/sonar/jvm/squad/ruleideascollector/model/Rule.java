package org.sonar.jvm.squad.ruleideascollector.model;

import java.time.LocalDateTime;
import java.util.Set;
import lombok.Builder;
import lombok.Data;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Builder
@Document
// TODO: inherit from BaseEntity
public class Rule {

  @Id
  public String id;
  @CreatedDate
  public LocalDateTime creationTimestamp;
  @CreatedDate
  public LocalDateTime modifiedTimestamp;
  private User creator;
  private String title;
  private String description;
  private Set<String> languages;
  private String[] tags;
  private Status status;
  private Boolean isSonarWay;
  private Comment[] comments;
}
