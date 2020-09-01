package org.code.challenge.model.repository;

import org.code.challenge.model.entity.User;
import org.code.challenge.model.exception.DataAdditionException;

import java.util.List;

public interface IFollowersRepository {

    List<User> getUserCreators(User follower);

    List<User> getUserFollowers(User creator);

    void addNewFollowerToCreator(User follower,User creator) throws DataAdditionException;

}
