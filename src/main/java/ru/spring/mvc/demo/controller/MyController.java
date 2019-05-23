package ru.spring.mvc.demo.controller;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class MyController {

    @RequestMapping(value = "/message", method = RequestMethod.POST)
    public ModelAndView sendMessage(@RequestBody String string, @RequestParam Integer integer) {

        ModelAndView model = new ModelAndView("message");
        model.addObject("string", string);
        model.addObject("integer", integer);

        return model;
    }
}
