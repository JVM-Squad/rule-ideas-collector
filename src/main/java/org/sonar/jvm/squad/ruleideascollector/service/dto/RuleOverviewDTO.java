package org.sonar.jvm.squad.ruleideascollector.service.dto;

import java.util.Set;
import lombok.Builder;
import lombok.Value;
import org.sonar.jvm.squad.ruleideascollector.persistence.model.Status;

@Value
@Builder
public class RuleOverviewDTO {

   String title;

   Set<String> languages;
   String[] tags;
   Boolean isSonarWay;

   Status status;
}
