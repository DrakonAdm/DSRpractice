package com.csa.app.controller;

import com.csa.app.exceptions.NotFoundException;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("friend")
public class FriendController {
    private int counter = 4;
    private List<Map<String, String>> friends = new ArrayList<Map<String, String>>() {{
        add(new HashMap<String, String>(){{put("id", "1"); put("link", "text");}});
        add(new HashMap<String, String>(){{put("id", "2"); put("link", "text");}});
        add(new HashMap<String, String>(){{put("id", "3"); put("link", "text");}});
    }};

    @GetMapping
    public List<Map<String, String>> list(){
        return friends;
    }

    @GetMapping("/{id}")
    public Map<String, String> getFriend(@PathVariable String id){
        return friends.stream()
                .filter(friend -> friend.get("id").equals(id))
                .findFirst()
                .orElseThrow(NotFoundException::new);
    }

    @PostMapping("{id}")
    public Map<String, String> addFriend(@RequestBody Map<String, String> newFriend){
        newFriend.put("id", String.valueOf(counter++));

        friends.add(newFriend);
        return newFriend;
    }

    @PutMapping("{id}")
    public Map<String, String> updateFriend(@PathVariable String id, @RequestBody Map<String, String> newFriend){
        Map<String, String> friendFromDb = getFriend("id");

        friendFromDb.putAll(newFriend);
        friendFromDb.put("id", id);

        return friendFromDb;
    }

    @DeleteMapping("{id}")
    public void deleteFriend(@PathVariable String id){
        Map<String, String> friend = getFriend(id);

        friends.remove(friend);
    }
}
