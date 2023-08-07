package com.csa.app.controller;

import com.csa.app.dto.response.BriefPersonDto;
import com.csa.app.dto.response.exception.AppErrorDto;
import com.csa.app.dto.response.friend.InviteFriendDto;
import com.csa.app.exceptions.UnseenInvitesNotFoundException;
import com.csa.app.service.friend.FriendService;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api-friends")
public class FriendController {
    private final FriendService friendService;

    @GetMapping("/{personId}")
    public ResponseEntity<?> getFriendsAsBriefPersonDto(@PathVariable("personId") Long personId) {
        try {
            List<BriefPersonDto> friendsList = friendService.getFriendsList(personId);

            if (friendsList.isEmpty()) {
                AppErrorDto errorDto = new AppErrorDto(HttpStatus.NOT_FOUND.value(), "Друзья не найдены");
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorDto);
            }

            return ResponseEntity.ok(friendsList);
        } catch (EntityNotFoundException e) {
            AppErrorDto errorDto = new AppErrorDto(HttpStatus.NOT_FOUND.value(), e.getMessage());
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorDto);
        }
    }

    @PostMapping("/remove-friendship")
    public ResponseEntity<?> removeFriendShip(@RequestParam("personId") Long personId, @RequestParam("anotherId") Long anotherId) {
        String message = friendService.removeFriendShip(personId, anotherId);
        return ResponseEntity.ok(message);
    }

    @GetMapping("/unseen-invite-friends/{personId}")
    public ResponseEntity<?> getUnseenInviteFriends(@PathVariable("personId") Long personId) {
        try {
            List<InviteFriendDto> unseenInviteFriends = friendService.getUnseenInviteFriends(personId);
            return ResponseEntity.ok(unseenInviteFriends);
        } catch (UnseenInvitesNotFoundException e) {
            // Обработка ошибки: отправить клиенту сообщение об ошибке
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(new AppErrorDto(HttpStatus.NOT_FOUND.value(), e.getMessage()));
        }
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