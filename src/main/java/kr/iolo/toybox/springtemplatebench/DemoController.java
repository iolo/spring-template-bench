package kr.iolo.toybox.springtemplatebench;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * @author iolo
 */
@Controller
@RequestMapping
public class DemoController {

    private final DemoService demoService;
    private final String profilesActive;

    @Autowired
    public DemoController(
            DemoService demoService,
            @Value("${spring.profiles.active}") String profilesActive) {
        this.demoService = demoService;
        this.profilesActive = profilesActive;
    }

    @RequestMapping("/")
    public String server(Model model) {
        model.addAttribute("title", profilesActive);
        model.addAttribute("comments", demoService.getComments());
        return "server";
    }

    @Autowired
    ReactDomServerNashorn reactDOMServerNashorn;

    @RequestMapping("/react/nashorn")
    public String reactNashorn(Model model) throws Exception {
        model.addAttribute("title", profilesActive);
        model.addAttribute("reactHtml", reactDOMServerNashorn.render(demoService.getComments()));
        return "react";
    }

    @Autowired
    ReactDomServerV8 reactDOMServerV8;

    @RequestMapping("/react/v8")
    public String reactV8(Model model) throws Exception {
        model.addAttribute("title", profilesActive);
        model.addAttribute("reactHtml", reactDOMServerV8.render(demoService.getComments()));
        return "react";
    }

    // BETTER ISOMORPHIC but v8 + ejs only!
    @RequestMapping("/react/v8ejs")
    public String v8(Model model) {
        model.addAttribute("title", profilesActive);
        model.addAttribute("comments", demoService.getComments());
        return "react_v8ejs";
    }

    @RequestMapping("/apis/v1/comments")
    @ResponseBody
    public List<Comment> comments() {
        return demoService.getComments();
    }

}
