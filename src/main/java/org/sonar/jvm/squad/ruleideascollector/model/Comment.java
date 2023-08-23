package org.sonar.jvm.squad.ruleideascollector.model;

import org.springframework.data.mongodb.core.mapping.Document;

@Document
public class Comment extends BaseEntity {

  private User creator;

  public User getCreator() {
    return creator;
  }

  public void setCreator(User creator) {
    this.creator = creator;
  }

  private String text;

  public String getText() {
    return text;
  }

  public void setText(String text) {
    this.text = text;
  }
}
