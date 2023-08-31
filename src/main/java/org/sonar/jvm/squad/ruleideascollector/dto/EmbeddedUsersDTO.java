package org.sonar.jvm.squad.ruleideascollector.dto;

import java.util.List;

public record EmbeddedUsersDTO(
  List<UserDTO> users
) {
}
