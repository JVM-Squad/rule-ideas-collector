package org.sonar.jvm.squad.ruleideascollector.service.dto;

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
}
