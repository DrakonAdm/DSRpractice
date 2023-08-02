package com.csa.app.service.friend;

import com.csa.app.dto.response.BriefPersonDto;
import com.csa.app.dto.response.exception.AppErrorDto;
import com.csa.app.dto.response.friend.InviteFriendDto;
import com.csa.app.entity.model.Invite;
import com.csa.app.entity.model.InviteStatus;
import com.csa.app.entity.model.Person;
import com.csa.app.exceptions.AppException;
import com.csa.app.repository.InviteRepo;
import com.csa.app.service.person.PersonService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class FriendService {
    private final PersonService personService;
    private final InviteRepo inviteRepository;

    public List<BriefPersonDto> getFriendsList(Long personId) {
        Person person = personService.findPersonById(personId);
        return person.getFriends().stream()
                .map(friend -> new BriefPersonDto(friend.getId(), friend.getName(), friend.getSurname(), friend.getDate()))
                .collect(Collectors.toList());
    }

    public String removeFriendShip(Long personId, Long anotherId) {
        Person person1 = personService.findPersonById(personId);
        Person person2 = personService.findPersonById(anotherId);

        Set<Person> personFriends = person1.getFriends();
        Set<Person> anotherFriends = person2.getFriends();

        if (personFriends.remove(person2) && anotherFriends.remove(person1)) {
            personService.saveStateChange(person1);
            personService.saveStateChange(person2);
            return "Удаление из друзей прошло успешно";
        } else {
            throw new AppException(HttpStatus.BAD_REQUEST, "Пользователи не найдены");
        }
    }

    public List<InviteFriendDto> getUnseenInviteFriends(Long personId) {
        List<Invite> unseenInvites = inviteRepository.findByToIdAndStatus(personId, InviteStatus.UNSEEN);
        return unseenInvites.stream()
                .map(invite -> {
                    Person fromPerson = personService.findPersonById(invite.getFromId());
                    return new InviteFriendDto(invite.getId(), fromPerson.getId(), fromPerson.getName(), fromPerson.getSurname(), fromPerson.getDate());
                })
                .collect(Collectors.toList());
    }

    public String addFriendship(Long personId, Long anotherId) {
        Person person1 = personService.findPersonById(personId);
        Person person2 = personService.findPersonById(anotherId);

        if (person1.getFriends().contains(person2) || person2.getFriends().contains(person1)) {
            throw new AppException(HttpStatus.BAD_REQUEST, "Дружба уже существует");
        }

        Invite invite = new Invite();
        invite.setFromId(person1.getId());
        invite.setToId(person2.getId());
        invite.setStatus(InviteStatus.UNSEEN);
        inviteRepository.save(invite);

        return "Приглашение в друзья отправлено";
    }

    public String createFriendship(Long inviteId) {
        Invite invite = inviteRepository.findById(inviteId).orElse(null);
        if (invite == null) {
            throw new AppException(HttpStatus.BAD_REQUEST, "Приглашение не найдено");
        }

        Person person1 = personService.findPersonById(invite.getFromId());
        Person person2 = personService.findPersonById(invite.getToId());

        if (person1 == null || person2 == null) {
            throw new AppException(HttpStatus.BAD_REQUEST, "Пользователи не найдены");
        }

        if (person1.getFriends().contains(person2) || person2.getFriends().contains(person1)) {
            throw new AppException(HttpStatus.BAD_REQUEST, "Дружба уже существует");
        }

        person1.getFriends().add(person2);
        person2.getFriends().add(person1);
        personService.saveStateChange(person1);
        personService.saveStateChange(person2);

        invite.setStatus(InviteStatus.ACCEPTED);
        inviteRepository.save(invite);

        return "Дружба успешно добавлена";
    }

    public String deleteInvite(Long inviteId) {
        Invite invite = inviteRepository.findById(inviteId).orElse(null);
        if (invite == null) {
            throw new AppException(HttpStatus.BAD_REQUEST, "Приглашение не найдено");
        }

        inviteRepository.delete(invite);
        return "Приглашение успешно удалено";
    }
}