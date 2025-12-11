package fr.sidranie.training;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import fr.sidranie.training.room.Room;
import fr.sidranie.training.room.RoomService;
import fr.sidranie.training.room.data.roomName.RoomName;
import fr.sidranie.training.user.User;
import fr.sidranie.training.user.UserService;
import fr.sidranie.training.user.data.firstName.FirstName;
import fr.sidranie.training.user.data.lastName.LastName;
import fr.sidranie.training.user.data.password.Password;
import fr.sidranie.training.user.data.username.Username;

public class TestHibernate {
    private static final Logger LOGGER = LoggerFactory.getLogger(TestHibernate.class);

    public static void main(String[] args) {
        UserService userService = new UserService();
        RoomService roomService = new RoomService();

        User user = new User(
            new Username("john_doe"),
            new Password("securePassword123"),
            new FirstName("John"),
            new LastName("Doe")
        );
        userService.registerUser(user);

        userService.getAllUsers().forEach(result -> LOGGER.debug("{}", result));

        User foundUser = userService.getUserById(1L);
        if (foundUser != null) {
            LOGGER.info("User found {}", foundUser.toString());
        } else {
            LOGGER.warn("User not found");
        }

        Room room = new Room(new RoomName("Mulhouse"));
        roomService.createRoom(room);
        roomService.getAllRooms().forEach(result -> LOGGER.debug("{}", result));
    }
}
