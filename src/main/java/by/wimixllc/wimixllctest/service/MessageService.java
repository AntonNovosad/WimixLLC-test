package by.wimixllc.wimixllctest.service;

import by.wimixllc.wimixllctest.dto.MessageResponseDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;

@Service
public class MessageService {
    private final SimpMessagingTemplate messagingTemplate;

    public MessageService(SimpMessagingTemplate messagingTemplate) {
        this.messagingTemplate = messagingTemplate;
    }

    public void notifyFrontend(final String message) {
        MessageResponseDto response = new MessageResponseDto(message);
        messagingTemplate.convertAndSend("/topic/messages", response);
    }

    public void notifyUser(final String id, final String message) {
        MessageResponseDto response = new MessageResponseDto(message);
        messagingTemplate.convertAndSendToUser(id, "/topic/private-messages", response);
    }
}
