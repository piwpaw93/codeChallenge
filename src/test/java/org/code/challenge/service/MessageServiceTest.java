package org.code.challenge.service;

import org.code.challenge.boot.ConfigurationProperties;
import org.code.challenge.model.entity.Message;
import org.code.challenge.model.entity.User;
import org.code.challenge.model.exception.DataAdditionException;
import org.code.challenge.model.exception.DataExtractionException;
import org.code.challenge.model.repository.IMessageRepository;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
public class MessageServiceTest {

    @Mock
    private IMessageRepository messageRepository;

    @Mock
    private IUserService userService;

    @Mock
    private ConfigurationProperties configurationProperties;

    private static Integer TEST_MESSAGE_MAX_LENGTH = 10;

    @Test
    public void testPostingNewMessage() throws DataExtractionException, DataAdditionException {
        //given
        String userName = "Test";
        User testUser = new User(userName);
        String messageText = "Text";
        MessageService messageService = new MessageService(messageRepository,userService,configurationProperties);
        //when
        Mockito.when(userService.getSpecifiedUser(userName)).thenReturn(testUser);
        Mockito.when(configurationProperties.getMessageLength()).thenReturn(TEST_MESSAGE_MAX_LENGTH);
        messageService.postNewMessage(userName,messageText);
        //then
        Mockito.verify(messageRepository,Mockito.times(1)).postNewMessage(testUser,messageText);
    }

    @Test(expected = DataAdditionException.class)
    public void testPostingTooLongMessage() throws DataExtractionException, DataAdditionException {
        //given
        String userName = "Test";
        User testUser = new User(userName);
        String messageText = "Very Long Text Message";
        MessageService messageService = new MessageService(messageRepository,userService,configurationProperties);
        //when
        Mockito.when(userService.getSpecifiedUser(userName)).thenReturn(testUser);
        Mockito.when(configurationProperties.getMessageLength()).thenReturn(TEST_MESSAGE_MAX_LENGTH);
        messageService.postNewMessage(userName,messageText);
    }


    @Test
    public void testGettingUserMessages() throws DataExtractionException {
        //given
        String userName = "Test";
        User testUser = new User(userName);
        List<Message> userMessages= new ArrayList<>();
        userMessages.add(new Message("Test 1"));
        userMessages.add(new Message("Test 2"));
        userMessages.add(new Message("Test 3"));
        String firstMessageText = "Test";
        Message firstMessage = new Message(firstMessageText);
        firstMessage.setTimeStamp(LocalDateTime.now().plusDays(1));
        userMessages.add(firstMessage);
        userMessages.add(new Message("Test 4"));
        MessageService messageService = new MessageService(messageRepository,userService,configurationProperties);
        //when
        Mockito.when(userService.getSpecifiedUser(userName)).thenReturn(testUser);
        Mockito.when(messageRepository.getUserMessages(testUser)).thenReturn(userMessages);
        List<Message> resultMessages = messageService.getUserMessages(userName);
        //then
        Assertions.assertNotNull(resultMessages);
        Assertions.assertEquals(resultMessages.size(),5);
        Assertions.assertEquals(resultMessages.get(0).getMessageText(),firstMessageText);
    }

    @Test
    public void testGettingUserCreatorMessages() throws DataExtractionException {
        //given
        String userName = "Test";
        User testUser = new User(userName);
        List<Message> userMessages= new ArrayList<>();
        userMessages.add(new Message("Test 1"));
        userMessages.add(new Message("Test 2"));
        String firstMessageText = "Test";
        Message firstMessage = new Message(firstMessageText);
        firstMessage.setTimeStamp(LocalDateTime.now().plusDays(1));
        userMessages.add(firstMessage);
        userMessages.add(new Message("Test 3"));
        MessageService messageService = new MessageService(messageRepository,userService,configurationProperties);
        //when
        Mockito.when(userService.getSpecifiedUser(userName)).thenReturn(testUser);
        Mockito.when(messageRepository.getUsersCreatorsMessages(testUser)).thenReturn(userMessages);
        List<Message> resultMessages = messageService.getUserCreatorMessages(userName);
        //then
        Assertions.assertNotNull(resultMessages);
        Assertions.assertEquals(resultMessages.size(),4);
        Assertions.assertEquals(resultMessages.get(0).getMessageText(),firstMessageText);
    }

}
