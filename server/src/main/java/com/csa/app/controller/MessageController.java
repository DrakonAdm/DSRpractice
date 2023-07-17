package com.csa.app.controller;

import com.csa.app.exceptions.NotFoundException;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("message")
public class MessageController {
    private int counter = 1;

    private List<Map<String, String>> messages = new ArrayList<Map<String, String>>() {{
        add(new HashMap<String, String>(){{put("FirstId", "1"); put("SecondId", "2");}});
        add(new HashMap<String, String>(){{put("FirstId", "1"); put("SecondId", "3");}});
        add(new HashMap<String, String>(){{put("FirstId", "1"); put("SecondId", "4");}});
    }};

    @GetMapping
    public List<Map<String, String>> list(){
        return messages;
    }

    @GetMapping("/{id}")
    public Map<String, String> getMessage(@PathVariable String id){
        return messages.stream()
                .filter(message -> message.get("FirstId").equals(id))
                .findFirst()
                .orElseThrow(NotFoundException::new);
    }

    @PostMapping
    public Map<String, String> addMessage(@RequestBody Map<String, String> newMessage){
        newMessage.put("FirstId", "1");
        newMessage.put("SecondId", String.valueOf(counter));

        messages.add(newMessage);
        return newMessage;
    }

    @PutMapping("/{id}")
    public Map<String, String> updateMessage(@PathVariable String id, @RequestBody Map<String, String> newMessage){
        Map<String, String> messageFromDb = getMessage(id);

        messageFromDb.putAll(newMessage);
        messageFromDb.put("FirstId", "1");

        return messageFromDb;
    }

    @DeleteMapping("/{id}")
    public void deleteMessage(@PathVariable String id){
        Map<String, String> message = getMessage(id);

        messages.remove(message);
    }
}
