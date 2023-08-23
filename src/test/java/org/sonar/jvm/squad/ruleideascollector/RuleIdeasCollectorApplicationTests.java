package org.sonar.jvm.squad.ruleideascollector;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.data.mongo.AutoConfigureDataMongo;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
@AutoConfigureDataMongo
class RuleIdeasCollectorApplicationTests {

	@Test
	void contextLoads() {
	}

}
