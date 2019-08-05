package com.lc.demoapp.jfairyandfakerdemo;

import io.codearte.jfairy.Fairy;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Locale;

@Controller
public class controllerApp {
    /**
     * http://localhost:8080/helloFairy
     */
    @RequestMapping("helloFairy")
    @ResponseBody
    public String helloFairy() {
        Fairy enFairy = Fairy.create();
// Locale.ENGLISH is default
        Fairy zhFairy = Fairy.create(Locale.forLanguageTag("zh"));
// Polish version
        return zhFairy.company().getName();
    }
}
