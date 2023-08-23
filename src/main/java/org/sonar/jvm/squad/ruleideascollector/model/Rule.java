package org.sonar.jvm.squad.ruleideascollector.model;

import java.util.Set;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
public class Rule extends BaseEntity {

  private User creator;

  public User getCreator() {
    return creator;
  }

  public void setCreator(User creator) {
    this.creator = creator;
  }

  private String ruleId;

  public String getRuleId() {
    return ruleId;
  }

  public void setRuleId(String ruleId) {
    this.ruleId = ruleId;
  }

  private String title;

  public String getTitle() {
    return title;
  }

  public void setTitle(String title) {
    this.title = title;
  }

  private Set<String> languages;

  public Set<String> getLanguages() {
    return languages;
  }

  public void setLanguages(Set<String> languages) {
    this.languages = languages;
  }

  private String[] tags;

  public String[] getTags() {
    return tags;
  }

  public void setTags(String[] tags) {
    this.tags = tags;
  }

  private Status status;

  public Status getStatus() {
    return status;
  }

  public void setStatus(Status status) {
    this.status = status;
  }

  private Boolean isSonarWay;

  public Boolean getSonarWay() {
    return isSonarWay;
  }

  public void setSonarWay(Boolean sonarWay) {
    isSonarWay = sonarWay;
  }

  private String description;

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  private Comment[] comments;

  public Comment[] getComments() {
    return comments;
  }

  public void setComments(Comment[] comments) {
    this.comments = comments;
  }
}
