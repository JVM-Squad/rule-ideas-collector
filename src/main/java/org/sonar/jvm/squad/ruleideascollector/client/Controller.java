package org.sonar.jvm.squad.ruleideascollector.client;

import org.sonar.jvm.squad.ruleideascollector.service.RuleService;
import org.sonar.jvm.squad.ruleideascollector.service.dto.RuleDTO;
import org.sonar.jvm.squad.ruleideascollector.service.dto.RuleOverviewDTO;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.view.RedirectView;

import java.time.LocalDateTime;

@org.springframework.stereotype.Controller
public class Controller {

    private RuleService ruleService;

    public Controller(RuleService ruleService) {
        this.ruleService = ruleService;
    }


    @GetMapping("/")
    public String showRuleList(
            @RequestParam(required = false) String ruleId,
            Model model
    ) {
        RestTemplate rt = new RestTemplate();
        model.addAttribute("rules", rt.getForEntity("http://localhost:8080/rules", RuleDTO[].class).getBody());
        if (ruleId != null) {
            model.addAttribute("selectedRule",
                    rt.getForEntity("http://localhost:8080/rules/" + ruleId, RuleDTO.class).getBody());
        }
        //model.addAttribute("rules", ruleService.getRules());
        return "index";
    }

    @PostMapping("/")
    public RedirectView saveRule(
            @RequestParam String title,
            @RequestParam String description,
            @RequestParam(required = false) String id,
            Model model
    ) {
        RestTemplate rt = new RestTemplate();
        if (id != null && id.isBlank()) id = null;
        RuleDTO ruleDTO = new RuleDTO(id, new RuleOverviewDTO(title, null, null, null, null), null, null, LocalDateTime.now(), description, null);
        String url = "http://localhost:8080/rules";
        if (id == null) {
            id = rt.postForEntity(url, ruleDTO, String.class).getBody();
        } else {
            rt.put(url, ruleDTO, RuleDTO.class);
        }


        return new RedirectView("http://localhost:8080?ruleId=" + id);
    }
}
