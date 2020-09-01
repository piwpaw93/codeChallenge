package org.code.challenge.model.repository;

import org.code.challenge.model.entity.Followers;
import org.code.challenge.model.entity.User;
import org.code.challenge.model.exception.DataAdditionException;
import org.springframework.stereotype.Component;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class FollowersMemoryRepository implements IFollowersRepository{

    private List<Followers> followersList;

    public FollowersMemoryRepository(){
        this.followersList = new ArrayList<>();
    }

    public List<User> getUserCreators(User follower){
       return followersList.stream()
               .filter(followers -> followers.getFollower().getName().equals(follower.getName()))
               .map(Followers::getCreator)
               .collect(Collectors.toList());
    }

    public List<User> getUserFollowers(User creator){
        return followersList.stream()
                .filter(followers -> followers.getCreator().getName().equals(creator.getName()))
                .map(Followers::getFollower)
                .collect(Collectors.toList());
    }

    public void addNewFollowerToCreator(User follower,User creator) throws DataAdditionException {
        Followers followers = new Followers(follower,creator);
        if(!followersList.contains(followers)){
            followersList.add(followers);
        } else {
            throw new DataAdditionException("Follower-creator relation already exist");
        }
    }


}
