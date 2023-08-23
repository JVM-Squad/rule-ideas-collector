package org.sonar.jvm.squad.ruleideascollector.model;

import org.springframework.data.mongodb.core.mapping.Document;

@Document
public class User extends BaseEntity {

  private String name;

  public String getName() {
    return name;
  }

  public void setName(String value) {
    name = value;
  }
}
