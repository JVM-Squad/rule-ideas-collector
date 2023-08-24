package org.sonar.jvm.squad.ruleideascollector.rest;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.sonar.jvm.squad.ruleideascollector.repo.RuleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import static org.sonar.jvm.squad.ruleideascollector.TestUtils.RULE_1;
import static org.sonar.jvm.squad.ruleideascollector.TestUtils.RULE_2;
import static org.sonar.jvm.squad.ruleideascollector.TestUtils.RULE_WITH_INVALID_ID;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
@WebMvcTest
class RuleControllerTest {

  @Autowired private MockMvc mockMvc;
  @Autowired private ObjectMapper objectMapper;
  @MockBean private RuleRepository ruleRepository;

  @Test
  void test_getRules() throws Exception {
    Mockito.when(ruleRepository.findAll()).thenReturn(List.of(RULE_1, RULE_2));

    mockMvc.perform(get("/rules").contentType("application/json"))
      .andExpect(status().isOk())
      //TODO test content of the body that matches the expecte rules
      .andDo(print());
  }

  @Test
  void test_createNewRuleSuccess() throws Exception {
    Mockito.when(ruleRepository.save(RULE_1)).thenReturn(RULE_1);

    String requestBody = objectMapper.writeValueAsString(RULE_1);

    mockMvc.perform(post("/rules").contentType("application/json").content(requestBody))
      .andExpect(status().isOk())
      .andDo(print());
  }

  @Test
  void test_createNewRuleFailure() throws Exception {
    String requestBody = objectMapper.writeValueAsString(RULE_WITH_INVALID_ID);

    mockMvc.perform(post("/rules").contentType("application/json").content(requestBody))
      .andExpect(status().isBadRequest())
      .andDo(print());
  }
}