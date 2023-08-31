package org.sonar.jvm.squad.ruleideascollector.persistence;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.sonar.jvm.squad.ruleideascollector.dto.UserResponseDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.testcontainers.junit.jupiter.Testcontainers;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.sonar.jvm.squad.ruleideascollector.TestUtils.USER_1;
import static org.sonar.jvm.squad.ruleideascollector.TestUtils.USER_DTO_1;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@Testcontainers
class UserRepositoryRestConfigureTest extends ContainerBase {

  @Value(value = "${local.server.port}")
  private int port;
  @Autowired
  private ObjectMapper objectMapper;
  @Autowired
  private UserRepository userRepository;

  @Test
  @DisplayName("get /users returns all users available in UserRepository")
  void test_saveUserAndGetUser() throws Exception {
    userRepository.save(USER_1);
    assertThat(userRepository.findAll()).containsExactlyElementsOf(List.of(USER_1));

    Response response = RestAssured.get("http://localhost:" + port + "/users");
    assertEquals(200, response.getStatusCode());
    var responseBodyAsDTO = objectMapper.readValue(response.asString(), UserResponseDTO.class);
    assertThat(responseBodyAsDTO._embedded().users()).containsExactlyElementsOf(List.of(USER_DTO_1));
  }
}
