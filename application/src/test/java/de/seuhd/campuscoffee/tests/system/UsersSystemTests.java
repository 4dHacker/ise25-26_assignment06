package de.seuhd.campuscoffee.tests.system;

import java.util.List;

import org.junit.jupiter.api.Test;

import de.seuhd.campuscoffee.domain.model.User;
import de.seuhd.campuscoffee.domain.tests.TestFixtures;
import static de.seuhd.campuscoffee.tests.SystemTestUtils.Requests.userRequests;

public class UsersSystemTests extends AbstractSysTest {


    @Test
    void createUser() {
        User userToCreate = TestFixtures.getUserListForInsertion().getFirst();
        User createdUser = userDtoMapper.toUser(userRequests.create(List.of(userDtoMapper.toUserDto(userToCreate))).getFirst());

        assertEqualsIgnoringIdAndTimestamps(createdUser, userToCreate);
    }

    @Test
    void getAllCreatedUsers() {
        List<User> createdUserList = TestFixtures.createUsers(userService);

        // Retrieve all users via API

        List<User> retrievedUsers = userRequests.retrieveAll()

                .stream()
                .map(userDtoMapper::toUser)
                .toList();


        // Assert that all created users are retrieved

        assertEqualsIgnoringTimestamps(retrievedUsers, createdUserList);
    }

    @Test
    void getUserById() {
        List<User> createdUserList = TestFixtures.createUsers(userService);
        User createdUser = createdUserList.getFirst();

        // Retrieve the first user by ID via API

        User retrievedUser = userDtoMapper.toUser(
                userRequests.retrieveById(createdUser.id())
        );

        // Assert that the retrieved user matches the created one
        assertEqualsIgnoringTimestamps(retrievedUser, createdUser);
    }

    @Test
    void getUserName() {

        // Create test users using the service
        List<User> createdUserList = TestFixtures.createUsers(userService);
        User createdUser = createdUserList.get(1); // Use second user
        // Retrieve user by login name via API filter endpoint
        User retrievedUser = userDtoMapper.toUser(
                userRequests.retrieveByFilter("name", createdUser.loginName())
        );
        // Assert that the retrieved user matches the created one
        assertEqualsIgnoringTimestamps(retrievedUser, createdUser);
    }

}