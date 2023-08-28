package org.sonar.jvm.squad.ruleideascollector.persistence;

import org.sonar.jvm.squad.ruleideascollector.persistence.model.User;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

// TODO: expose UserDTO to REST API instead of User directly.
@RepositoryRestResource(path = "users")
public interface UserRepository extends MongoRepository<User, String> {
}
