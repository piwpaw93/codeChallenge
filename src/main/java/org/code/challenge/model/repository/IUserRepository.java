package org.code.challenge.model.repository;

import org.code.challenge.model.entity.User;
import org.code.challenge.model.exception.DataAdditionException;
import org.code.challenge.model.exception.DataExtractionException;



public interface IUserRepository {

    User getUser(String name) throws DataExtractionException;

    void addNewUser(String name) throws DataAdditionException;
}
