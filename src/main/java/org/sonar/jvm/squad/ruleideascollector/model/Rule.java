package org.sonar.jvm.squad.ruleideascollector.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import java.util.Set;

@Entity
@Table(name = "rules")
public class Rule extends BaseEntity {

  @Column(name = "creator")
  private User creator;

  public User getCreator() {
    return creator;
  }

  public void setCreator(User creator) {
    this.creator = creator;
  }

  @Column(name = "rule_id")
  private String ruleId;

  public String getRuleId() {
    return ruleId;
  }

  public void setRuleId(String ruleId) {
    this.ruleId = ruleId;
  }

  @Column(name = "title")
  private String title;

  public String getTitle() {
    return title;
  }

  public void setTitle(String title) {
    this.title = title;
  }

  @Column(name = "languages")
  private Set<String> languages;

  public Set<String> getLanguages() {
    return languages;
  }

  public void setLanguages(Set<String> languages) {
    this.languages = languages;
  }

  @Column(name = "tags")
  private String[] tags;

  public String[] getTags() {
    return tags;
  }

  public void setTags(String[] tags) {
    this.tags = tags;
  }

  @Column(name = "status")
  private Status status;

  public Status getStatus() {
    return status;
  }

  public void setStatus(Status status) {
    this.status = status;
  }

  @Column(name = "is_sonar_way")
  private Boolean isSonarWay;

  public Boolean getSonarWay() {
    return isSonarWay;
  }

  public void setSonarWay(Boolean sonarWay) {
    isSonarWay = sonarWay;
  }

  @Column(name = "description")
  private String description;

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  @Column(name = "comments")
  private Comment[] comments;


  public Comment[] getComments() {
    return comments;
  }

  public void setComments(Comment[] comments) {
    this.comments = comments;
  }
}
