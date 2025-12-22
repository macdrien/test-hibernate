package fr.sidranie.training;

import java.time.LocalDate;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import fr.sidranie.training.reservation.Reservation;
import fr.sidranie.training.reservation.ReservationRepository;
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

    static void main(String[] args) {
        UserService userService = new UserService();
        RoomService roomService = new RoomService();
        ReservationService reservationService = new ReservationService(new ReservationRepository());

        LocalDate today = LocalDate.now();

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

        Reservation reservation = new Reservation(user, albi,
                new ReservationBeginDate(today),
                new ReservationEndDate(today.plusDays(3)));
        reservationService.createReservation(reservation);
        reservationService.getAll().forEach(result -> LOGGER.debug("{}", result));

        try { // Reservation with end date before begin date
            Reservation invalidReservation = new Reservation(user, mulhouse,
                    new ReservationBeginDate(today.plusDays(2)),
                    new ReservationEndDate(today));
            reservationService.createReservation(invalidReservation);
        } catch (IllegalArgumentException e) {
            LOGGER.error("Failed to create reservation: {}", e.getMessage());
        }

        try { // Reservation will conflict with the existing one
            Reservation overlappingReservation = new Reservation(user, albi,
                    new ReservationBeginDate(today.plusDays(2)),
                    new ReservationEndDate(today.plusDays(4)));
            reservationService.createReservation(overlappingReservation);
        } catch (IllegalArgumentException e) {
            LOGGER.error("Failed to create reservation: {}", e.getMessage());
        }

        try { // The reservation starts in the past
            Reservation pastReservation = new Reservation(user, mulhouse,
                    new ReservationBeginDate(today.minusDays(2)),
                    new ReservationEndDate(today.plusDays(2)));
            reservationService.createReservation(pastReservation);
        } catch (IllegalArgumentException e) {
            LOGGER.error("Failed to create reservation: {}", e.getMessage());
        }
    }
}
