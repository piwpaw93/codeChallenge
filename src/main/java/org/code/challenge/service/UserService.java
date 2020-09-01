package org.code.challenge.service;

import lombok.Getter;
import lombok.Setter;
import org.code.challenge.model.entity.User;
import org.code.challenge.model.repository.IFollowersRepository;
import org.code.challenge.model.repository.IUserRepository;
import org.code.challenge.model.exception.DataAdditionException;
import org.code.challenge.model.exception.DataExtractionException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Getter
@Setter
public class UserService implements IUserService {

    private IUserRepository userRepository;

    private IFollowersRepository followersRepository;

    @Autowired
    public UserService(IUserRepository userRepository, IFollowersRepository followersRepository) {
        this.userRepository = userRepository;
        this.followersRepository = followersRepository;
    }

    @Override
    public void registerNewUser(String name) throws DataAdditionException {
        userRepository.addNewUser(name);
    }

    @Override
    public boolean isUserRegistered(String name) {
        try {
            userRepository.getUser(name);
        } catch (DataExtractionException ex) {
            return false;
        }
        return true;
    }

    @Override
    public User getSpecifiedUser(String name) throws DataExtractionException {
        return userRepository.getUser(name);
    }

    @Override
    public List<User> getUsersFollowers(String name) throws DataExtractionException {
        User user = userRepository.getUser(name);
        return followersRepository.getUserFollowers(user);
    }

    @Override
    public List<User> getUsersCreators(String name) throws DataExtractionException {
        User user = userRepository.getUser(name);
        return followersRepository.getUserCreators(user);
    }

    @Override
    public void followCreator(String followerName, String creatorName) throws DataExtractionException, DataAdditionException {
        User follower = userRepository.getUser(followerName);
        User creator = userRepository.getUser(creatorName);
        if(creator.equals(follower)){
            throw new DataAdditionException("Follower and creator can't be the same user");
        }
        followersRepository.addNewFollowerToCreator(follower,creator);
    }
}
