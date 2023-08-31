package org.sonar.jvm.squad.ruleideascollector.dto;

import java.util.Arrays;
import java.util.Objects;
import lombok.Builder;

import java.time.LocalDateTime;

@Builder
public record RuleDTO(
        String id,

        RuleOverviewDTO ruleOverviewDTO,

        UserDTO creator,
        LocalDateTime creationTimestamp,
        LocalDateTime modifiedTimestamp,

        String description,
        CommentDTO[] comments
) {
  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    RuleDTO ruleDTO = (RuleDTO) o;
    return Objects.equals(id, ruleDTO.id) && Objects.equals(ruleOverviewDTO, ruleDTO.ruleOverviewDTO) && Objects.equals(creator, ruleDTO.creator) && Objects.equals(creationTimestamp, ruleDTO.creationTimestamp) && Objects.equals(modifiedTimestamp, ruleDTO.modifiedTimestamp) && Objects.equals(description, ruleDTO.description) && Arrays.equals(comments, ruleDTO.comments);
  }

  @Override
  public int hashCode() {
    int result = Objects.hash(id, ruleOverviewDTO, creator, creationTimestamp, modifiedTimestamp, description);
    result = 31 * result + Arrays.hashCode(comments);
    return result;
  }

  @Override
  public String toString() {
    return "RuleDTO{" +
      "id='" + id + '\'' +
      ", ruleOverviewDTO=" + ruleOverviewDTO +
      ", creator=" + creator +
      ", creationTimestamp=" + creationTimestamp +
      ", modifiedTimestamp=" + modifiedTimestamp +
      ", description='" + description + '\'' +
      ", comments=" + Arrays.toString(comments) +
      '}';
  }
}
