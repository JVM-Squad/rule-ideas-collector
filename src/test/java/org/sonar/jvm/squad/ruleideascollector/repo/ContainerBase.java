package org.sonar.jvm.squad.ruleideascollector.repo;
import org.springframework.test.context.DynamicPropertySource;
import org.testcontainers.containers.GenericContainer;
import org.testcontainers.containers.wait.strategy.Wait;
import org.testcontainers.lifecycle.Startables;

public abstract class ContainerBase {

  static final GenericContainer mongo;

  static {
    mongo = new GenericContainer("mongo:latest")
      .withExposedPorts(27017)
      .waitingFor(Wait.forLogMessage(".*Waiting for connections.*\\n", 1))
      .withEnv("MONGO_INITDB_ROOT_USERNAME", "admin")
      .withReuse(true)
      .withEnv("MONGO_INITDB_ROOT_PASSWORD", "password")
      .withEnv("MONGO_INITDB_ROOT_PASSWORD", "password");
  }

  static {
    Startables.deepStart(mongo).join();
  }

  @DynamicPropertySource
  static void mongoProperties(org.springframework.test.context.DynamicPropertyRegistry registry) {
    registry.add("spring.data.mongodb.host", mongo::getHost);
    registry.add("spring.data.mongodb.port", mongo::getFirstMappedPort);
    registry.add("spring.data.mongodb.authentication-database", () -> "admin");
    registry.add("spring.data.mongodb.database", () -> "rule_ideas_collector_test");
    registry.add("spring.data.mongodb.username", () -> "admin");
    registry.add("spring.data.mongodb.password", () -> "password");
  }

}