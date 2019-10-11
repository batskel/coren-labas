package by.sci.controller;


import by.sci.laba2.AppRunner;
import by.sci.laba2.Node;
import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.annotation.SessionScope;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping(value = "/")
@SessionAttributes("nodes")
public class HomeController {

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String home(ModelMap map) {
        return "home";
    }

    @RequestMapping(value = "/get", method = RequestMethod.GET, produces = "application/json")
    @ResponseBody
    public String getLab(@RequestParam String code,ModelMap map) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        List<Node> nodes = AppRunner.getElementsCode(code);
        map.addAttribute("nodes",nodes);
        String json = mapper.writeValueAsString(nodes);
        return json;
    }

    @RequestMapping(value = "/getCode")
    @ResponseBody
    public String getCode(@RequestParam String code,@SessionAttribute("nodes") List<Node> list) {
        return AppRunner.getCode(list, code);
    }


}
