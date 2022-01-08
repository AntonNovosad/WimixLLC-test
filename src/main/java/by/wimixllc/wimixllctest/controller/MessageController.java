package by.wimixllc.wimixllctest.controller;

import by.wimixllc.wimixllctest.entity.Message;
import by.wimixllc.wimixllctest.service.MessageService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/profile")
public class MessageController {

    private final MessageService service;

    public MessageController(MessageService service) {
        this.service = service;
    }

    @PostMapping("/user/send-message")
    public void sendMessage(@RequestBody final Message message) {
        service.notifyFrontend(message.getMessage());
    }

    @PostMapping("/user/send-private-message/{id}")
    public void sendPrivateMessage(@PathVariable final String id,
                                   @RequestBody final Message message) {
        service.notifyUser(id, message.getMessage());
    }
}
