package org.sonar.jvm.squad.ruleideascollector.persistence;

import org.sonar.jvm.squad.ruleideascollector.persistence.model.User;
import org.sonar.jvm.squad.ruleideascollector.service.MapperUtils;
import org.sonar.jvm.squad.ruleideascollector.service.dto.UserDTO;
import org.springframework.core.convert.support.ConfigurableConversionService;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.data.rest.core.config.RepositoryRestConfiguration;
import org.springframework.data.rest.webmvc.config.RepositoryRestConfigurer;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.config.annotation.CorsRegistry;

// TODO: expose UserDTO to REST API instead of User directly.
@RepositoryRestResource(path = "users")
public interface UserRepository extends MongoRepository<User, String> {
}

@Component
class UserRepositoryRestConfigurer implements RepositoryRestConfigurer {
  @Override
  public void configureRepositoryRestConfiguration(RepositoryRestConfiguration config, CorsRegistry cors) {
    config.exposeIdsFor(User.class);
    config.setDefaultPageSize(500);
  }

  @Override
  public void configureConversionService(ConfigurableConversionService conversionService) {
    // TODO: is there a way to convert the User to UserDTO for transfer? Does the converter have anything to do with it?
    conversionService.addConverter(User.class, UserDTO.class, MapperUtils::fromModel);
  }
}
