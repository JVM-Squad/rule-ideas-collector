package org.sonar.jvm.squad.ruleideascollector.repo;

import org.sonar.jvm.squad.ruleideascollector.rest.RuleController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.testcontainers.junit.jupiter.Testcontainers;

@SpringBootTest
@Testcontainers
public class RuleControllerTest {

  @Autowired
  RuleController ruleController;

}
