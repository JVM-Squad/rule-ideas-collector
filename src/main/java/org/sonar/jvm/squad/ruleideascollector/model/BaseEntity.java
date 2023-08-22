package org.sonar.jvm.squad.ruleideascollector.model;

import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.MappedSuperclass;
import java.io.Serializable;

@MappedSuperclass
public class BaseEntity implements Serializable {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  public String id;

  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  @Column(name = "creation_timestamp")
  public long creationTimestamp;

  public long getCreationTimestamp() {
    return creationTimestamp;
  }

  public void setCreationTimestamp(long value) {
    creationTimestamp = value;
  }

  @Column(name = "modified_timestamp")
  public long modifiedTimestamp;

  public long getModifiedTimestamp() {
    return modifiedTimestamp;
  }

  public void setModifiedTimestamp(long value) {
    modifiedTimestamp = value;
  }
}
