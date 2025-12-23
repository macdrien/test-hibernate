package fr.sidranie.training.reservation;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrowsExactly;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.time.LocalDate;
import java.time.LocalDateTime;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;

import fr.sidranie.training.MockedTestCase;
import fr.sidranie.training.reservation.data.reservationBeginDate.ReservationBeginDate;
import fr.sidranie.training.reservation.data.reservationEndDate.ReservationEndDate;
import fr.sidranie.training.room.Room;
import fr.sidranie.training.room.data.roomName.RoomName;
import fr.sidranie.training.user.User;
import fr.sidranie.training.user.data.firstName.FirstName;
import fr.sidranie.training.user.data.lastName.LastName;
import fr.sidranie.training.user.data.password.Password;
import fr.sidranie.training.user.data.username.Username;

class ReservationServiceMockedTest extends MockedTestCase {

    @Mock
    private ReservationRepository reservationRepository;

    @Spy @InjectMocks
    private ReservationService reservationService;

    private User defaultUser = new User(
            new Username("jdoe"),
            new Password("pass"),
            new FirstName("john"),
            new LastName("doe"));
    private Room defaultRoom = new Room(new RoomName("Test"));

    @Test
    public void testCreateReservationNominal() {
        LocalDateTime testStartDateTime = LocalDateTime.now();

        Reservation reservation = new Reservation(
                defaultUser,
                defaultRoom,
                new ReservationBeginDate(LocalDate.now()),
                new ReservationEndDate(LocalDate.now().plusDays(2)));

        reservationService.createReservation(reservation);

        assertNotNull(reservation.getReservationDateTime());
        assertTrue(testStartDateTime.isBefore(reservation.getReservationDateTime().getValue()));

        verify(reservationRepository, times(1)).save(any(Reservation.class));
    }

    @Test
    public void testCreateReservationWithNullArgument() {
        assertThrowsExactly(IllegalArgumentException.class, () -> reservationService.createReservation(null));
    }

    @Test
    public void testCreateReservationWithoutReservationDates() {
        Reservation reservation = new Reservation(defaultUser, defaultRoom,
                null, new ReservationEndDate(LocalDate.now()));
        assertThrowsExactly(IllegalArgumentException.class, () -> reservationService.createReservation(reservation));

        reservation.setReservationBeginDate(new ReservationBeginDate(LocalDate.now()));
        reservation.setReservationEndDate(null);
        assertThrowsExactly(IllegalArgumentException.class, () -> reservationService.createReservation(reservation));
    }

    @Test
    public void testCreateReservationWithEndBeforeBeginning() {
        Reservation reservation = new Reservation(defaultUser, defaultRoom,
                new ReservationBeginDate(LocalDate.now().plusDays(1)), new ReservationEndDate(LocalDate.now()));
        assertThrowsExactly(IllegalArgumentException.class, () -> reservationService.createReservation(reservation));
    }

    @Test
    public void testCreateReservationWithBeginningBeforeNow() {
        Reservation reservation = new Reservation(defaultUser, defaultRoom,
                new ReservationBeginDate(LocalDate.now().minusDays(1)),
                new ReservationEndDate(LocalDate.now().plusDays(1)));
        assertThrowsExactly(IllegalArgumentException.class, () -> reservationService.createReservation(reservation));
    }

    @Test
    public void testCreateReservationWithReservedRoom() {
        ReservationBeginDate reservationBeginDate = new ReservationBeginDate(LocalDate.now());
        ReservationEndDate reservationEndDate = new ReservationEndDate(LocalDate.now().plusDays(1));
        Reservation reservation = new Reservation(defaultUser, defaultRoom,
                reservationBeginDate,
                reservationEndDate);

        when(reservationRepository.existsReservationForRoomInInterval(
                defaultRoom,
                reservationBeginDate.getValue(),
                reservationEndDate.getValue()))
                .thenReturn(true);

        assertThrowsExactly(IllegalArgumentException.class, () -> reservationService.createReservation(reservation));
    }
}