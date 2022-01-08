package by.wimixllc.wimixllctest.controller;

import by.wimixllc.wimixllctest.dto.MessageResponseDto;
import by.wimixllc.wimixllctest.entity.Message;
import by.wimixllc.wimixllctest.entity.User;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.annotation.SendToUser;
import org.springframework.stereotype.Controller;
import org.springframework.web.util.HtmlUtils;

@Controller
public class ChatController {

    @MessageMapping("/message")
    @SendTo("/chat/messages")
    public MessageResponseDto getMessage(final Message message) {
        return new MessageResponseDto(HtmlUtils.htmlEscape(message.getMessage()));
    }

    @MessageMapping("/private-message")
    @SendToUser("/chat/private-messages")
    public MessageResponseDto getPrivateMessage(final Message message, final User user) {
        return new MessageResponseDto(HtmlUtils.htmlEscape(
                "Sending private message to user " + user.getId() + ": "
                        + message.getMessage())
        );
    }
}
