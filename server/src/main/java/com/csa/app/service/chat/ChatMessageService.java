package com.csa.app.service.chat;

import com.csa.app.entity.message.ChatMessage;
import com.csa.app.entity.message.MessageStatus;
import com.csa.app.exceptions.ResourceNotFoundException;
import com.csa.app.repository.ChatMessageRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.CriteriaUpdate;
import jakarta.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;

@Service
public class ChatMessageService {
    @Autowired
    private ChatMessageRepo repository;

    @Autowired
    private ChatRoomService chatRoomService;

    @PersistenceContext
    private EntityManager entityManager;

    public ChatMessage save(ChatMessage chatMessage) {
        chatMessage.setStatus(MessageStatus.RECEIVED);
        repository.save(chatMessage);
        return chatMessage;
    }

    public long countNewMessages(String senderId, String recipientId) {
        // Использование JPA Criteria API для создания запроса
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<Long> query = cb.createQuery(Long.class);
        Root<ChatMessage> root = query.from(ChatMessage.class);
        query.select(cb.count(root));
        query.where(
                cb.and(
                        cb.equal(root.get("senderId"), senderId),
                        cb.equal(root.get("recipientId"), recipientId),
                        cb.equal(root.get("status"), MessageStatus.RECEIVED)
                )
        );
        return entityManager.createQuery(query).getSingleResult();
    }

    public List<ChatMessage> findChatMessages(String senderId, String recipientId) {
        var chatId = chatRoomService.getChatId(senderId, recipientId, false);

        var messages =
                chatId.map(cId -> repository.findByChatId(cId)).orElse(new ArrayList<>());

        if(messages.size() > 0) {
            updateStatuses(senderId, recipientId, MessageStatus.DELIVERED);
        }

        return messages;
    }

    public ChatMessage findById(String id) {
        return repository
                .findById(id)
                .map(chatMessage -> {
                    chatMessage.setStatus(MessageStatus.DELIVERED);
                    return repository.save(chatMessage);
                })
                .orElseThrow(() ->
                        new ResourceNotFoundException("can't find message (" + id + ")"));
    }

    public void updateStatuses(String senderId, String recipientId, MessageStatus status) {
        // Использование JPA Criteria API для создания запроса
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaUpdate<ChatMessage> update = cb.createCriteriaUpdate(ChatMessage.class);
        Root<ChatMessage> root = update.from(ChatMessage.class);
        update.set(root.get("status"), status);
        update.where(
                cb.and(
                        cb.equal(root.get("senderId"), senderId),
                        cb.equal(root.get("recipientId"), recipientId)
                )
        );
        entityManager.createQuery(update).executeUpdate();
    }
}