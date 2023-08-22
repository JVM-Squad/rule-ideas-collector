package org.sonar.jvm.squad.ruleideascollector.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(name = "users")
public class User extends BaseEntity {

  @Column(name = "name")
  private String name;

  public String getName() {
    return name;
  }

  public void setName(String value) {
    name = value;
  }
}
