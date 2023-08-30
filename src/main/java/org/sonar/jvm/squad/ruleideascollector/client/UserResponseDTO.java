package org.sonar.jvm.squad.ruleideascollector.client;

import java.util.List;
import org.sonar.jvm.squad.ruleideascollector.service.dto.UserDTO;

public record UserResponseDTO (
  EmbeddedUsersDTO _embedded
) {
}

record EmbeddedUsersDTO (
  List<UserDTO> users
) {}
