package org.code.challenge.model.repository;

import org.code.challenge.model.entity.User;
import org.code.challenge.model.exception.DataAdditionException;
import org.code.challenge.model.exception.DataExtractionException;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class UserMemoryRepository implements IUserRepository {

    private List<User> userList;

    public UserMemoryRepository() {
        this.userList = new ArrayList<>();
    }

    public User getUser(String name) throws DataExtractionException {
        if (userExistsInRepository(name)) {
            return userList.stream().filter(user -> user.getName().equals(name)).findFirst().get();
        } else {
            throw new DataExtractionException(name, User.class);
        }
    }

    public void addNewUser(String name) throws DataAdditionException {
        User newUser = new User(name);
        if (!userExistsInRepository(name)) {
            userList.add(newUser);
        } else {
            throw new DataAdditionException(new StringBuilder("User with name: ").append(name).append(" already exists.").toString());
        }
    }

    private boolean userExistsInRepository(String name) {
        return userList.stream().anyMatch(user -> user.getName().equals(name));
    }

}
