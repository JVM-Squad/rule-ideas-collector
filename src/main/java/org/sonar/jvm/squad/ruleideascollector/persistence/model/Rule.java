package org.sonar.jvm.squad.ruleideascollector.persistence.model;

import java.time.LocalDateTime;
import java.util.Set;
import lombok.Builder;
import lombok.Data;
import org.sonar.jvm.squad.ruleideascollector.dto.Status;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Builder
@Document
public class Rule {

  @Id
  private String id;
  @CreatedDate
  private LocalDateTime creationTimestamp;
  @CreatedDate
  private LocalDateTime modifiedTimestamp;
  private User creator;
  private String title;
  private String description;
  private Set<String> languages;
  private String[] tags;
  private Status status;
  private Boolean isSonarWay;
  private Comment[] comments;
}
