package com.csa.app.controller;

import com.csa.app.exceptions.NotFoundException;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("this-profile")
public class ThisProfileController {

    private Map<String, String> informations = new HashMap<String, String>() {{
        put("id", "1");
        put("FirstName", "text");
        put("SecondName", "text");
        put("DateBirth", "text");
        put("City", "text");
        put("Country", "text");
        put("mail", "text");
        put("Phone", "text");
    }};

    @GetMapping
    public Map<String, String> list(){
        return informations;
    }

    @GetMapping("/{key}")
    public String getInformation(@PathVariable String key){
        if(informations.containsKey(key)){
            return informations.get(key);
        } else {
            throw new NotFoundException();
        }
    }

    @PutMapping("/{key}")
    public Map<String, String> updateInformation(@PathVariable String key, @RequestBody String value){
        if(informations.containsKey(key)){
            informations.put(key, value);
            return informations;
        } else {
            throw new NotFoundException();
        }
    }
}