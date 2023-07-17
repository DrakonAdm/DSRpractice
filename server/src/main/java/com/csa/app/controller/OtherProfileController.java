package com.csa.app.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("other-profile")
public class OtherProfileController {

    private Map<String, String> informations = new HashMap<String, String>() {{
        put("id", "1");
        put("FirstName", "text");
        put("SecondName", "text");
        put("DateBirth", "text");
        put("City", "text");
        put("Country", "text");
    }};

    @GetMapping
    public Map<String, String> list(){
        return informations;
    }
}