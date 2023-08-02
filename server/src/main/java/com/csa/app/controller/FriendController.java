package com.csa.app.controller;

import com.csa.app.dto.response.BriefPersonDto;
import com.csa.app.dto.response.friend.InviteFriendDto;
import com.csa.app.service.friend.FriendService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api-friends")
public class FriendController {
    private final FriendService friendService;

    @GetMapping("/{personId}")
    public ResponseEntity<?> getFriendsAsBriefPersonDto(@PathVariable("personId") Long personId) {
        List<BriefPersonDto> friendsList = friendService.getFriendsList(personId);
        return ResponseEntity.ok(friendsList);
    }

    @PostMapping("/remove-friendship")
    public ResponseEntity<?> removeFriendShip(@RequestParam("personId") Long personId, @RequestParam("anotherId") Long anotherId) {
        String message = friendService.removeFriendShip(personId, anotherId);
        return ResponseEntity.ok(message);
    }

    @GetMapping("/unseen-invite-friends/{personId}")
    public ResponseEntity<?> getUnseenInviteFriends(@PathVariable("personId") Long personId) {
        List<InviteFriendDto> unseenInviteFriends = friendService.getUnseenInviteFriends(personId);
        return ResponseEntity.ok(unseenInviteFriends);
    }

    @PostMapping("/add-friendship")
    public ResponseEntity<?> addFriendship(@RequestParam("personId") Long personId, @RequestParam("anotherId") Long anotherId) {
        String message = friendService.addFriendship(personId, anotherId);
        return ResponseEntity.ok(message);
    }

    @PostMapping("/create-friendship")
    public ResponseEntity<?> createFriendship(@RequestParam("inviteId") Long inviteId) {
        String message = friendService.createFriendship(inviteId);
        return ResponseEntity.ok(message);
    }

    @PostMapping("/delete-invite")
    public ResponseEntity<?> deleteInvite(@RequestParam("inviteId") Long inviteId) {
        String message = friendService.deleteInvite(inviteId);
        return ResponseEntity.ok(message);
    }
}