package org.sonar.jvm.squad.ruleideascollector.model;

import java.time.LocalDateTime;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;

public class BaseEntity {

  @Id
  public String id;
}
