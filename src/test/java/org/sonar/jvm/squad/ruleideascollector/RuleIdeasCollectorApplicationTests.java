package org.sonar.jvm.squad.ruleideascollector;

import org.junit.jupiter.api.Test;
import org.sonar.jvm.squad.ruleideascollector.model.Rule;
import org.sonar.jvm.squad.ruleideascollector.repo.RuleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class RuleIdeasCollectorApplicationTests {

	@Test
	void contextLoads() {
	}

	@Autowired
	RuleRepository repo;

	@Test
	void testPersist() {
		var rule = new Rule();
		rule.setId("1ABC");
		rule.setRuleId("S1234");
		rule.setTitle("ABC");

		repo.save(rule);
		assertThat(repo.findAll().size()).isEqualTo(1);
	}
}
