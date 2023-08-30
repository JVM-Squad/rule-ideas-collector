package org.sonar.jvm.squad.ruleideascollector.service.dto;

import java.util.List;

public record EmbeddedUsersDTO(
  List<UserDTO> users
) {
}
