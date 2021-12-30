package by.wimixllc.wimixllctest.controller;

import by.wimixllc.wimixllctest.entity.Message;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ChatController {

    @MessageMapping("/message")
    @SendTo("/chat/messages")
    public Message getMessages(Message message) {
        System.out.println(message);
        return message;
    }
}
