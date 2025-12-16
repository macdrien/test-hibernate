package fr.sidranie.training;

import java.time.LocalDate;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import fr.sidranie.training.reservation.Reservation;
import fr.sidranie.training.reservation.ReservationService;
import fr.sidranie.training.reservation.data.reservationBeginDate.ReservationBeginDate;
import fr.sidranie.training.reservation.data.reservationEndDate.ReservationEndDate;
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
        ReservationService reservationService = new ReservationService();

        User user = new User(
            new Username("john_doe"),
            new Password("securePassword123"),
            new FirstName("John"),
            new LastName("Doe")
        );
        userService.registerUser(user);
        userService.getAllUsers().forEach(result -> LOGGER.debug("{}", result));

        Room mulhouse = new Room(new RoomName("Mulhouse"));
        roomService.createRoom(mulhouse);
        Room albi = new Room(new RoomName("Albi"));
        roomService.createRoom(albi);
        roomService.getAllRooms().forEach(result -> LOGGER.debug("{}", result));

        Reservation reservation = new Reservation(user,
                Set.of(mulhouse, albi),
                new ReservationBeginDate(LocalDate.of(2025, 12, 10)),
                new ReservationEndDate(LocalDate.of(2025, 12, 12)));
        reservationService.createReservation(reservation);
        reservationService.getAllReservations().forEach(result -> LOGGER.debug("{}", result));

        try {
            Reservation invalidReservation = new Reservation(user,
                    Set.of(mulhouse),
                    new ReservationBeginDate(LocalDate.of(2025, 12, 15)),
                    new ReservationEndDate(LocalDate.of(2025, 12, 14)));
            reservationService.createReservation(invalidReservation);
        } catch (IllegalArgumentException e) {
            LOGGER.error("Failed to create reservation: {}", e.getMessage());
        }
    }
}
