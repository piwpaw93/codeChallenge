package org.code.challenge.service;

import org.code.challenge.boot.ConfigurationProperties;
import org.code.challenge.model.entity.Message;
import org.code.challenge.model.entity.User;
import org.code.challenge.model.exception.DataExtractionException;
import org.code.challenge.model.repository.IMessageRepository;
import org.code.challenge.model.exception.DataAdditionException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class MessageService implements IMessageService {

    private IMessageRepository messageRepository;

    private IUserService userService;

    private ConfigurationProperties configurationProperties;

    @Autowired
    public MessageService(IMessageRepository messageRepository,IUserService userService,ConfigurationProperties configurationProperties) {
        this.messageRepository = messageRepository;
        this.userService = userService;
        this.configurationProperties = configurationProperties;
    }

    @Override
    public void postNewMessage(String userName, String messageText) throws DataAdditionException, DataExtractionException {
        if(messageText.length() > configurationProperties.getMessageLength()) {
            throw new DataAdditionException("Message is to long to post!");
        }
        if(!userService.isUserRegistered(userName)){
            userService.registerNewUser(userName);
        }
        User user = userService.getSpecifiedUser(userName);
        messageRepository.postNewMessage(user,messageText);
    }

    @Override
    public List<Message> getUserMessages(String userName) throws DataExtractionException {
        User user = userService.getSpecifiedUser(userName);
        return messageRepository.getUserMessages(user).stream()
                .sorted((msg1,msg2)-> msg2.getTimeStamp().compareTo(msg1.getTimeStamp()))
                .collect(Collectors.toList());

    }

    @Override
    public List<Message> getUserCreatorMessages(String userName) throws DataExtractionException {
        User user = userService.getSpecifiedUser(userName);
        return messageRepository.getUsersCreatorsMessages(user).stream()
                .sorted((msg1,msg2)-> msg2.getTimeStamp().compareTo(msg1.getTimeStamp()))
                .collect(Collectors.toList());
    }
}
