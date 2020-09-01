package org.code.challenge.service;

import org.code.challenge.model.entity.User;
import org.code.challenge.model.exception.DataAdditionException;
import org.code.challenge.model.exception.DataExtractionException;

import java.util.List;

public interface IUserService {

    void registerNewUser(String name) throws DataAdditionException;

    boolean isUserRegistered(String name);

    User getSpecifiedUser(String name) throws DataExtractionException;

    List<User> getUsersFollowers(String name) throws DataExtractionException;

    List<User> getUsersCreators(String name) throws DataExtractionException;

    void followCreator(String followerName, String creatorName) throws DataExtractionException, DataAdditionException;
}
