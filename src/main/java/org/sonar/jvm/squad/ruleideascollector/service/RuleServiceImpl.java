package org.sonar.jvm.squad.ruleideascollector.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;
import org.sonar.jvm.squad.ruleideascollector.persistence.RuleRepository;
import org.sonar.jvm.squad.ruleideascollector.service.dto.RuleDTO;
import org.sonar.jvm.squad.ruleideascollector.service.dto.RuleOverviewDTO;
import org.springframework.stereotype.Service;

@Service
public class RuleServiceImpl implements RuleService {

  private final RuleRepository ruleRepository;
  public RuleServiceImpl(RuleRepository ruleRepository) {
    this.ruleRepository = ruleRepository;
  }

  @Override
  public List<RuleDTO> getRules() {
    return getRulesAsStream().toList();
  }

  @Override
  public List<RuleOverviewDTO> getRuleOverviews() {
    return getRulesAsStream()
      .map(RuleDTO::getRuleOverviewDTO)
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
    return rule.getId();
  }

  @Override
  public boolean modifyRule(RuleDTO ruleDTO) {
    if(ruleRepository.existsById(ruleDTO.getId())) {
      ruleRepository.save(MapperUtils.fromDTO(ruleDTO));
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
