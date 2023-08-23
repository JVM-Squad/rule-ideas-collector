package org.sonar.jvm.squad.ruleideascollector.model;

import java.time.LocalDateTime;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;

public class BaseEntity {

  @Id
  public String id;

  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  @CreatedDate
  public LocalDateTime creationTimestamp;

  public LocalDateTime getCreationTimestamp() {
    return creationTimestamp;
  }

  public void setCreationTimestamp(LocalDateTime value) {
    creationTimestamp = value;
  }

  @CreatedDate
  public LocalDateTime modifiedTimestamp;

  public LocalDateTime getModifiedTimestamp() {
    return modifiedTimestamp;
  }

  public void setModifiedTimestamp(LocalDateTime value) {
    modifiedTimestamp = value;
  }
}
