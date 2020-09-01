package org.code.challenge.service;

import org.code.challenge.model.entity.User;
import org.code.challenge.model.exception.DataAdditionException;
import org.code.challenge.model.exception.DataExtractionException;
import org.code.challenge.model.repository.IFollowersRepository;
import org.code.challenge.model.repository.IUserRepository;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.ArrayList;
import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
public class UserServiceTest {

    @Mock
    private IUserRepository userRepository;

    @Mock
    private IFollowersRepository followersRepository;

    @Test
    public void testAddingNewUser() throws DataAdditionException {
        //given
        String userName = "Test";
        UserService userService = new UserService(userRepository,followersRepository);
        //when
        userService.registerNewUser(userName);
        //then
        Mockito.verify(userRepository,Mockito.times(1)).addNewUser(userName);
    }

    @Test(expected = DataAdditionException.class)
    public void testAddingExistingUser() throws DataAdditionException {
        //given
        String userName = "Test";
        UserService userService = new UserService(userRepository,followersRepository);
        //when
        Mockito.doThrow(new DataAdditionException("User not found!")).when(userRepository).addNewUser(userName);
        userService.registerNewUser(userName);
    }

    @Test
    public void testIfUserIsRegistered() throws DataExtractionException {
        //given
        String userName = "Test";
        User testUser = new User(userName);
        //when
        UserService userService = new UserService(userRepository,followersRepository);
        Mockito.when(userRepository.getUser(userName)).thenReturn(testUser);
        //then
        Assertions.assertTrue(userService.isUserRegistered(userName));
    }

    @Test
    public void testIfUserIsNotRegistered() throws DataExtractionException {
        //given
        String userName = "Test";
        UserService userService = new UserService(userRepository,followersRepository);
        //when
        Mockito.doThrow(new DataExtractionException(userName,User.class)).when(userRepository).getUser(userName);
        //then
        Assertions.assertFalse(userService.isUserRegistered(userName));
    }

    @Test
    public void testGettingUser() throws DataExtractionException {
        //given
        String userName = "Test";
        User testUser = new User(userName);
        UserService userService = new UserService(userRepository,followersRepository);
        //when
        Mockito.when(userRepository.getUser(userName)).thenReturn(testUser);
        //then
        Assertions.assertEquals(userService.getSpecifiedUser(userName).getName(),userName);
    }

    @Test(expected = DataExtractionException.class)
    public void testGettingWrongUser() throws DataExtractionException {
        //given
        String userName = "Test";
        UserService userService = new UserService(userRepository,followersRepository);
        //when
        Mockito.doThrow(new DataExtractionException(userName,User.class)).when(userRepository).getUser(userName);
        userService.getSpecifiedUser(userName);
    }

    @Test
    public void testGettingFollowers() throws DataExtractionException {
        //given
        String userName = "Test";
        User testUser = new User(userName);
        List<User> followers = new ArrayList<>();
        followers.add(new User("Test 2"));
        followers.add(new User("Test 3"));
        //when
        UserService userService = new UserService(userRepository,followersRepository);
        Mockito.when(userRepository.getUser(userName)).thenReturn(testUser);
        Mockito.when(followersRepository.getUserFollowers(testUser)).thenReturn(followers);
        //then
        List<User> followersResult = userService.getUsersFollowers(userName);
        Assertions.assertNotNull(followersResult);
        Assertions.assertEquals(followersResult.size(), 2);
    }

    @Test(expected = DataExtractionException.class)
    public void testGettingFollowersForNoExistingUser() throws DataExtractionException {
        //Given
        String userName = "Test";
        User testUser = new User(userName);
        //when
        UserService userService = new UserService(userRepository,followersRepository);
        Mockito.doThrow(new DataExtractionException(userName,User.class)).when(userRepository).getUser(userName);
        userService.getUsersFollowers(userName);
    }

    @Test
    public void testGettingCreators() throws DataExtractionException {
        //given
        String userName = "Test";
        User testUser = new User(userName);
        List<User> creators = new ArrayList<>();
        creators.add(new User("Test 2"));
        creators.add(new User("Test 3"));
        //when
        UserService userService = new UserService(userRepository,followersRepository);
        Mockito.when(userRepository.getUser(userName)).thenReturn(testUser);
        Mockito.when(followersRepository.getUserCreators(testUser)).thenReturn(creators);
        //then
        List<User> creatorsResult = userService.getUsersCreators(userName);
        Assertions.assertNotNull(creatorsResult);
        Assertions.assertEquals(creatorsResult.size(), 2);
    }

    @Test(expected = DataExtractionException.class)
    public void testGettingCreatorsForNoExistingUser() throws DataExtractionException {
        //given
        String userName = "Test";
        User testUser = new User(userName);
        UserService userService = new UserService(userRepository,followersRepository);
        //when
        Mockito.doThrow(new DataExtractionException(userName,User.class)).when(userRepository).getUser(userName);
        userService.getUsersFollowers(userName);
    }

    @Test
    public void testFollowingCreator() throws DataExtractionException,DataAdditionException {
        //given
        String userName = "Test";
        String creatorName = "Test 2";
        User testUser = new User(userName);
        User testCreator = new User(creatorName);
        //when
        UserService userService = new UserService(userRepository,followersRepository);
        Mockito.when(userRepository.getUser(userName)).thenReturn(testUser);
        Mockito.when(userRepository.getUser(creatorName)).thenReturn(testCreator);
        //then
        userService.followCreator(userName,creatorName);
        Mockito.verify(followersRepository,Mockito.times(1)).addNewFollowerToCreator(testUser,testCreator);
    }

    @Test(expected = DataExtractionException.class)
    public void testFollowingNonExistentCreator() throws DataExtractionException,DataAdditionException {
        //given
        String userName = "Test";
        String creatorName = "Test 2";
        User testUser = new User(userName);
        User testCreator = new User(creatorName);
        //when
        UserService userService = new UserService(userRepository,followersRepository);
        Mockito.when(userRepository.getUser(userName)).thenReturn(testUser);
        Mockito.doThrow(new DataExtractionException(testCreator,User.class)).when(userRepository).getUser(creatorName);
        //then
        userService.followCreator(userName,creatorName);
    }

    @Test(expected = DataAdditionException.class)
    public void testUserFollowingSameUser() throws DataExtractionException,DataAdditionException {
        //given
        String userName = "Test";
        User testUser = new User(userName);
        //when
        UserService userService = new UserService(userRepository,followersRepository);
        Mockito.when(userRepository.getUser(userName)).thenReturn(testUser);
        //then
        userService.followCreator(userName,userName);
    }
}
