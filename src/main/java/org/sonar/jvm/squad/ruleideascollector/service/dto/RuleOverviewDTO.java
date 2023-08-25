package org.sonar.jvm.squad.ruleideascollector.service.dto;

import java.util.Set;

import lombok.Builder;
import org.sonar.jvm.squad.ruleideascollector.persistence.model.Status;

@Builder
public record RuleOverviewDTO(

        String title,

        Set<String> languages,
        String[] tags,
        Boolean isSonarWay,

        Status status
) {
}
