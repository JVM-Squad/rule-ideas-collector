package org.sonar.jvm.squad.ruleideascollector.controller;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.List;
import java.util.Optional;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.sonar.jvm.squad.ruleideascollector.service.RuleService;
import org.sonar.jvm.squad.ruleideascollector.service.dto.RuleDTO;
import org.sonar.jvm.squad.ruleideascollector.service.dto.RuleOverviewDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import static org.assertj.core.api.Assertions.assertThat;
import static org.sonar.jvm.squad.ruleideascollector.TestUtils.RULE_DTO_1;
import static org.sonar.jvm.squad.ruleideascollector.TestUtils.RULE_DTO_2;
import static org.sonar.jvm.squad.ruleideascollector.TestUtils.RULE_DTO_WITH_INVALID_ID;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
@WebMvcTest
class RuleControllerTest {

  @Autowired
  private MockMvc mockMvc;
  @Autowired
  private ObjectMapper objectMapper;
  @MockBean
  private RuleService ruleService;

  @Test
  @DisplayName("get /rules returns all rules available in the Rule Service")
  void test_getRules() throws Exception {
    var ruleDTOs = List.of(RULE_DTO_1, RULE_DTO_2);
    Mockito.when(ruleService.getRules()).thenReturn(ruleDTOs);

   var result = mockMvc.perform(get("/rules").contentType("application/json"))
      .andExpect(status().isOk())
      .andDo(print())
     .andReturn();

   var responseBodyAsDTO = objectMapper.readValue(result.getResponse().getContentAsString(), new TypeReference<List<RuleDTO>>() {});
   assertThat(responseBodyAsDTO).isEqualTo(ruleDTOs);
  }

  @Test
  @DisplayName("get /rules returns all rules available in the Rule Service")
  void test_getRuleOverviews() throws Exception {
    var ruleOverviewDTOs = List.of(RULE_DTO_1.ruleOverviewDTO(), RULE_DTO_2.ruleOverviewDTO());
    Mockito.when(ruleService.getRuleOverviews()).thenReturn(ruleOverviewDTOs);

    var result = mockMvc.perform(get("/rules/overviews").contentType("application/json"))
      .andExpect(status().isOk())
      .andDo(print())
      .andReturn();

    var responseBodyAsDTO = objectMapper.readValue(result.getResponse().getContentAsString(), new TypeReference<List<RuleOverviewDTO>>() {});
    assertThat(responseBodyAsDTO).isEqualTo(ruleOverviewDTOs);
  }

  @Test
  @DisplayName("get /rules/{id} returns the rule with id {id} available in the Rule Service")
  void test_getRule() throws Exception {
    Mockito.when(ruleService.getRule(RULE_DTO_2.id())).thenReturn(Optional.of(RULE_DTO_2));

    var getRuleUrl = "/rules/"+RULE_DTO_2.id();
    var result = mockMvc.perform(get(getRuleUrl).contentType("application/json"))
      .andExpect(status().isOk())
      .andDo(print())
      .andReturn();

    var responseBodyAsDTO = objectMapper.readValue(result.getResponse().getContentAsString(), new TypeReference<RuleDTO>() {});
    assertThat(responseBodyAsDTO).isEqualTo(RULE_DTO_2);
  }

  @Test
  @DisplayName("post /rules saves a valid rule the Rule Service")
  void test_createNewRuleSuccess() throws Exception {
    Mockito.when(ruleService.createRule(RULE_DTO_1)).thenReturn(RULE_DTO_1.id());

    String requestBody = objectMapper.writeValueAsString(RULE_DTO_1);

    mockMvc.perform(post("/rules").contentType("application/json").content(requestBody))
      .andExpect(status().isOk())
      .andDo(print());
  }

  @Test
  @DisplayName("post /rules doesn't save a rule with missing id the Rule Service")
  void test_createNewRuleFailure() throws Exception {
    String requestBody = objectMapper.writeValueAsString(RULE_DTO_WITH_INVALID_ID);

    mockMvc.perform(post("/rules").contentType("application/json").content(requestBody))
      .andExpect(status().isBadRequest())
      .andDo(print());
  }

  @Test
  @DisplayName("put /rules modifies a rule in the Rule Service")
  void test_modifyNewRuleSuccess() throws Exception {
    Mockito.when(ruleService.modifyRule(RULE_DTO_1)).thenReturn(true);

    String requestBody = objectMapper.writeValueAsString(RULE_DTO_1);

    mockMvc.perform(put("/rules").contentType("application/json").content(requestBody))
      .andExpect(status().isOk());
  }

  @Test
  @DisplayName("put /rules doesn't modify a rule that doesn't exist the Rule Service")
  void test_modifyNewRuleFailure() throws Exception {
    Mockito.when(ruleService.modifyRule(RULE_DTO_1)).thenReturn(false);

    String requestBody = objectMapper.writeValueAsString(RULE_DTO_1);

    mockMvc.perform(put("/rules").contentType("application/json").content(requestBody))
      .andExpect(status().isBadRequest())
      .andDo(print());
  }
}