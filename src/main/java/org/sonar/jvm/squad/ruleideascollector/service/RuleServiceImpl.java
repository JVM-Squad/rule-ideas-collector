package org.sonar.jvm.squad.ruleideascollector.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;
import org.sonar.jvm.squad.ruleideascollector.persistence.RuleRepository;
import org.sonar.jvm.squad.ruleideascollector.persistence.UserRepository;
import org.sonar.jvm.squad.ruleideascollector.dto.RuleDTO;
import org.sonar.jvm.squad.ruleideascollector.dto.RuleOverviewDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RuleServiceImpl implements RuleService {

  private final RuleRepository ruleRepository;
  private final UserRepository userRepository;

  @Autowired
  public RuleServiceImpl(RuleRepository ruleRepository, UserRepository userRepository) {
    this.ruleRepository = ruleRepository;
    this.userRepository = userRepository;
  }

  @Override
  public List<RuleDTO> getRules() {
    return getRulesAsStream().toList();
  }

  @Override
  public List<RuleOverviewDTO> getRuleOverviews() {
    return getRulesAsStream()
      .map(RuleDTO::ruleOverviewDTO)
      .toList();
  }

  @Override
  public Optional<RuleDTO> getRule(String id) {
    return ruleRepository.findById(id)
      .map(MapperUtils::fromModel);
  }

  @Override
  public String createRule(RuleDTO ruleDTO) {
    var rule = ruleRepository.save(MapperUtils.fromDTO(ruleDTO));
    userRepository.save(MapperUtils.fromDTO(ruleDTO.creator()));
    return rule.getId();
  }

  @Override
  public boolean modifyRule(RuleDTO ruleDTO) {
    if(ruleRepository.existsById(ruleDTO.id())) {
      ruleRepository.save(MapperUtils.fromDTO(ruleDTO));
      userRepository.save(MapperUtils.fromDTO(ruleDTO.creator()));
      return true;
    }

    return false;
  }

  private Stream<RuleDTO> getRulesAsStream() {
   return ruleRepository.findAll()
      .stream()
      .map(MapperUtils::fromModel);
  }
}
