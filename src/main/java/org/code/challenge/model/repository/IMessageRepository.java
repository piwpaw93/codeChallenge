package org.code.challenge.model.repository;

import org.code.challenge.model.entity.Message;
import org.code.challenge.model.entity.User;

import java.util.List;

public interface IMessageRepository {

    List<Message> getUserMessages(User user);

    List<Message> getUsersCreatorsMessages(User user);

    void postNewMessage(User user, String messageText);
}
