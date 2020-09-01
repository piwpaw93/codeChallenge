package org.code.challenge.model.repository;

import lombok.Getter;
import lombok.Setter;
import org.code.challenge.model.entity.Message;
import org.code.challenge.model.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Component
@Setter
@Getter
public class MessageMemoryRepository implements IMessageRepository {

    private IUserRepository userRepository;

    private IFollowersRepository followersRepository;

    @Autowired
    public MessageMemoryRepository(IUserRepository userRepository, IFollowersRepository followersRepository) {
        this.userRepository = userRepository;
        this.followersRepository = followersRepository;
    }

    @Override
    public List<Message> getUserMessages(User user) {
        return user.getMessages();
    }

    @Override
    public List<Message> getUsersCreatorsMessages(User user) {
        return followersRepository.getUserCreators(user).stream().map(User::getMessages).flatMap(Collection::stream).collect(Collectors.toList());
    }

    @Override
    public void postNewMessage(User user,String messageText) {
        user.getMessages().add(new Message(messageText));
    }
}
