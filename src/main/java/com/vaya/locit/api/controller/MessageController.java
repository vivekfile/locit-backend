package com.vaya.locit.api.controller;

import com.vaya.locit.api.entity.Message;
import com.vaya.locit.api.service.MessageService;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/messages")
public class MessageController {

    private final MessageService messageService;

    public MessageController(
            MessageService messageService) {

        this.messageService = messageService;
    }

    // GET /messages
    @GetMapping
    public ResponseEntity<List<Message>> getAllMessages() {

        return ResponseEntity.ok(
                messageService.getAllMessages()
        );
    }

    // GET /messages/{id}
    @GetMapping("/{id}")
    public ResponseEntity<Message> getMessageById(
            @PathVariable int id) {

        Message message =
                messageService.getMessageById(id);

        if (message == null) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(message);
    }

    // GET /messages/conversation?buyerId=2&shopId=1
    @GetMapping("/conversation")
    public ResponseEntity<List<Message>> getConversation(
            @RequestParam int buyerId,
            @RequestParam int shopId) {

        return ResponseEntity.ok(
                messageService.getConversation(
                        buyerId,
                        shopId
                )
        );
    }

    // GET /messages/sender/{senderId}
    @GetMapping("/sender/{senderId}")
    public ResponseEntity<List<Message>> getMessagesBySender(
            @PathVariable int senderId) {

        return ResponseEntity.ok(
                messageService.getMessagesBySender(
                        senderId
                )
        );
    }

    // GET /messages/shop/{shopId}
    @GetMapping("/shop/{shopId}")
    public ResponseEntity<List<Message>> getMessagesByShop(
            @PathVariable int shopId) {

        return ResponseEntity.ok(
                messageService.getMessagesByShop(
                        shopId
                )
        );
    }

    // POST /messages
    @PostMapping
    public ResponseEntity<Message> sendMessage(
            @RequestBody Message message) {

        Message createdMessage =
                messageService.sendMessage(
                        message
                );

        return ResponseEntity.ok(createdMessage);
    }

    // PUT /messages/{id}
    @PutMapping("/{id}")
    public ResponseEntity<Message> updateMessage(
            @PathVariable int id,
            @RequestBody Message message) {

        Message updatedMessage =
                messageService.updateMessage(
                        id,
                        message
                );

        if (updatedMessage == null) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(updatedMessage);
    }

    // DELETE /messages/{id}
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteMessage(
            @PathVariable int id) {

        boolean deleted =
                messageService.deleteMessage(id);

        if (!deleted) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.noContent().build();
    }
}