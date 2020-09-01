package org.code.challenge.service;

import org.code.challenge.model.entity.Message;
import org.code.challenge.model.exception.DataAdditionException;
import org.code.challenge.model.exception.DataExtractionException;

import java.util.List;

public interface IMessageService {

    void postNewMessage(String userName, String messageText) throws DataAdditionException, DataExtractionException;

    List<Message> getUserMessages(String userName) throws DataExtractionException;

    List<Message> getUserCreatorMessages(String userName) throws DataExtractionException;
}
