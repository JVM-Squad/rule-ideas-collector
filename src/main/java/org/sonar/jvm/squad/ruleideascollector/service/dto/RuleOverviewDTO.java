package org.sonar.jvm.squad.ruleideascollector.service.dto;

import java.util.Arrays;
import java.util.Objects;
import java.util.Set;

import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import org.sonar.jvm.squad.ruleideascollector.persistence.model.Status;

@Builder
public record RuleOverviewDTO(

        String title,

        Set<String> languages,
        String[] tags,
        Boolean isSonarWay,

        Status status
) {
  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    RuleOverviewDTO that = (RuleOverviewDTO) o;
    return Objects.equals(title, that.title)
      && Objects.equals(languages, that.languages)
      && Arrays.equals(tags, that.tags)
      && Objects.equals(isSonarWay, that.isSonarWay) && status == that.status;
  }

  @Override
  public int hashCode() {
    int result = Objects.hash(title, languages, isSonarWay, status);
    result = 31 * result + Arrays.hashCode(tags);
    return result;
  }
}
