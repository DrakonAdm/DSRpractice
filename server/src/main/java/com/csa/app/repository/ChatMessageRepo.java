package com.csa.app.repository;

import com.csa.app.entity.message.ChatMessage;
import com.csa.app.entity.message.MessageStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ChatMessageRepo extends JpaRepository<ChatMessage, String> {
    List<ChatMessage> findByChatId(String chatId);
}
