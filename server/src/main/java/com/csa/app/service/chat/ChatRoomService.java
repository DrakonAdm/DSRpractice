package com.csa.app.service.chat;

import com.csa.app.dto.response.ListChatRoomDto;
import com.csa.app.entity.model.Person;
import com.csa.app.exceptions.ChatRoomsNotFoundException;
import com.csa.app.repository.ChatRoomRepo;
import com.csa.app.repository.PersonRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.csa.app.entity.message.ChatRoom;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ChatRoomService {

    @Autowired
    private ChatRoomRepo chatRoomRepository;

    public Optional<String> getChatId(
            String senderId, String recipientId, boolean createIfNotExist) {

        return chatRoomRepository
                .findBySenderIdAndRecipientId(senderId, recipientId)
                .map(ChatRoom::getChatId)
                .or(() -> {
                    if(!createIfNotExist) {
                        return  Optional.empty();
                    }
                    var chatId =
                            String.format("%s_%s", senderId, recipientId);

                    ChatRoom senderRecipient = ChatRoom
                            .builder()
                            .chatId(chatId)
                            .senderId(senderId)
                            .recipientId(recipientId)
                            .build();

                    ChatRoom recipientSender = ChatRoom
                            .builder()
                            .chatId(chatId)
                            .senderId(recipientId)
                            .recipientId(senderId)
                            .build();
                    chatRoomRepository.save(senderRecipient);
                    chatRoomRepository.save(recipientSender);

                    return Optional.of(chatId);
                });
    }

    @Autowired
    private PersonRepo personRepository;

    public List<ListChatRoomDto> getListChatRoom(String senderId) {
        List<ChatRoom> chatRooms = chatRoomRepository.findBySenderId(senderId);
        List<ListChatRoomDto> listChatRoomDto = new ArrayList<>();

        if (chatRooms.isEmpty()) {
            throw new ChatRoomsNotFoundException("Друзья не найдены");
        }

        for (ChatRoom chatRoom : chatRooms) {
            Long recipientId = Long.parseLong(chatRoom.getRecipientId());
            Person recipient = personRepository.findById(recipientId).orElse(null);

            if (recipient != null) {
                ListChatRoomDto dto = new ListChatRoomDto(
                        chatRoom.getId(),
                        chatRoom.getChatId(),
                        chatRoom.getSenderId(),
                        chatRoom.getRecipientId(),
                        recipient.getName(),
                        recipient.getSurname(),
                        recipient.getDate()
                );
                listChatRoomDto.add(dto);
            }
        }

        return listChatRoomDto;
    }
}
