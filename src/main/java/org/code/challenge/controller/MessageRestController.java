package org.code.challenge.controller;

import org.code.challenge.service.IMessageService;
import org.code.challenge.service.IUserService;
import org.code.challenge.model.entity.Message;
import org.code.challenge.model.exception.DataAdditionException;
import org.code.challenge.model.exception.DataExtractionException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/message")
public class MessageRestController {

    private IMessageService messageController;

    private IUserService userController;

    @Autowired
    public MessageRestController(IMessageService messageController, IUserService userController) {
        this.messageController = messageController;
        this.userController = userController;
    }

    @PostMapping("/newMessage")
    @ResponseBody
    public ResponseEntity<String> newMessage(@RequestParam String user,@RequestBody String messageText) throws DataAdditionException, DataExtractionException {
        messageController.postNewMessage(user,messageText);
        return ResponseEntity.ok("Message posted!");
    }

    @GetMapping("/myMessages")
    @ResponseBody
    public ResponseEntity<List<Message>> userMessages(@RequestParam String user) throws DataExtractionException {
        List<Message> messages = messageController.getUserMessages(user);
        return ResponseEntity.ok(messages);
    }

    @GetMapping("/myWall")
    @ResponseBody
    public ResponseEntity<List<Message>> userCreatorMessages(@RequestParam String user) throws DataExtractionException {
        List<Message> messages = messageController.getUserCreatorMessages(user);
        return ResponseEntity.ok(messages);
    }

}
