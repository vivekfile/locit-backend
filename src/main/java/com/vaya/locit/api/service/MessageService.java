package com.vaya.locit.api.service;

import com.vaya.locit.api.entity.Message;
import com.vaya.locit.api.repository.MessageRepository;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MessageService {

    private final MessageRepository messageRepository;

    public MessageService(
            MessageRepository messageRepository) {

        this.messageRepository = messageRepository;
    }

    public List<Message> getAllMessages() {

        return messageRepository.findAll();
    }

    public Message getMessageById(int id) {

        return messageRepository
                .findById(id)
                .orElse(null);
    }

    public List<Message> getConversation(
            int buyerId,
            int shopId) {

        return messageRepository
                .findByBuyerUserIdAndShopShopIdOrderBySentAtAsc(
                        buyerId,
                        shopId
                );
    }

    public List<Message> getMessagesBySender(
            int senderId) {

        return messageRepository
                .findBySenderUserId(senderId);
    }

    public List<Message> getMessagesByShop(
            int shopId) {

        return messageRepository
                .findByShopShopIdOrderBySentAtAsc(
                        shopId
                );
    }

    public Message sendMessage(Message message) {

        if (message.getMessageText() == null ||
                message.getMessageText().isBlank()) {

            throw new RuntimeException(
                    "Message cannot be empty"
            );
        }

        return messageRepository.save(message);
    }

    public Message updateMessage(
            int id,
            Message message) {

        Message existingMessage =
                messageRepository
                        .findById(id)
                        .orElse(null);

        if (existingMessage == null) {
            return null;
        }

        if (message.getMessageText() == null ||
                message.getMessageText().isBlank()) {

            throw new RuntimeException(
                    "Message cannot be empty"
            );
        }

        existingMessage.setMessageText(
                message.getMessageText()
        );

        return messageRepository.save(
                existingMessage
        );
    }

    public boolean deleteMessage(int id) {

        if (!messageRepository.existsById(id)) {
            return false;
        }

        messageRepository.deleteById(id);

        return true;
    }
}