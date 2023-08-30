package org.sonar.jvm.squad.ruleideascollector.client;

import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import org.sonar.jvm.squad.ruleideascollector.service.RuleService;
import org.sonar.jvm.squad.ruleideascollector.service.dto.RuleDTO;
import org.sonar.jvm.squad.ruleideascollector.service.dto.RuleOverviewDTO;
import org.sonar.jvm.squad.ruleideascollector.service.dto.UserDTO;
import org.sonar.jvm.squad.ruleideascollector.service.dto.UserResponseDTO;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.view.RedirectView;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

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
        model.addAttribute("users", rt.getForEntity("http://localhost:8080/users", UserResponseDTO.class).getBody()._embedded().users());

        /*ParameterizedTypeReference<RestResponsePage<UserDTO>> responseType = new ParameterizedTypeReference<>() { };
        ResponseEntity<RestResponsePage<UserDTO>> result = rt.exchange("http://localhost:8080/users", HttpMethod.GET, null, responseType);
        List<UserDTO> searchResult = result.getBody().getContent();
        model.addAttribute("users", searchResult);*/


        //model.addAttribute("users", rt.getForEntity("http://localhost:8080/users", UserDTO[].class).getBody());

        if (ruleId != null) {
            model.addAttribute("selectedRule",
                    rt.getForEntity("http://localhost:8080/rules/" + ruleId, RuleDTO.class).getBody());
        }

        return "index";
    }

    public static class RestResponsePage<T> extends PageImpl<T>{

        private static final long serialVersionUID = 3248189030448292002L;

        public RestResponsePage(List<T> content, Pageable pageable, long total) {
            super(content, pageable, total);
            // TODO Auto-generated constructor stub
        }

        public RestResponsePage(List<T> content) {
            super(content);
            // TODO Auto-generated constructor stub
        }

        /* PageImpl does not have an empty constructor and this was causing an issue for RestTemplate to cast the Rest API response
         * back to Page.
         */
        public RestResponsePage() {
            super(new ArrayList<T>());
        }

    }

    @PostMapping("/")
    public RedirectView saveRule(
            @RequestParam String title,
            @RequestParam String description,
            @RequestParam(required = false) String id,
            @RequestParam("user") String userId,
            Model model,
            HttpServletResponse response
    ) throws IOException {
        /*if (userId.isBlank()) {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Authoring user is a required field.");
            return null;
        }*/

        RestTemplate rt = new RestTemplate();
        if (id != null && id.isBlank()) id = null;
        UserDTO user = rt.getForEntity("http://localhost:8080/users/" + userId, UserDTO.class).getBody();
        RuleDTO ruleDTO = new RuleDTO(id, new RuleOverviewDTO(title, null, null, null, null), user, null, LocalDateTime.now(), description, null);
        String url = "http://localhost:8080/rules";
        if (id == null) {
            id = rt.postForEntity(url, ruleDTO, String.class).getBody();
        } else {
            rt.put(url, ruleDTO, RuleDTO.class);
        }

        return new RedirectView("http://localhost:8080?ruleId=" + id);
    }
}
