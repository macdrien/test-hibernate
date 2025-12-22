package fr.sidranie.training.reservation;

import java.util.List;

import fr.sidranie.training.reservation.data.reservationBeginDate.ReservationBeginDate;
import fr.sidranie.training.reservation.data.reservationDateTime.ReservationDateTime;
import fr.sidranie.training.reservation.data.reservationEndDate.ReservationEndDate;
import fr.sidranie.training.room.Room;

public class ReservationService {
    private final ReservationRepository reservationRepository;

    public ReservationService(ReservationRepository reservationRepository) {
        this.reservationRepository = reservationRepository;
    }

    public List<Reservation> getAll() {
        return reservationRepository.findAll();
    }

    public void createReservation(Reservation reservation) {
        if (reservation == null) {
            throw new IllegalArgumentException("Reservation cannot be null");
        }

        if (reservation.getReservationBeginDate() == null || reservation.getReservationEndDate() == null) {
            throw new IllegalArgumentException("Reservation dates cannot be null");
        }
        if (reservation.getReservationEndDate().getValue().isBefore(reservation.getReservationBeginDate().getValue())) {
            throw new IllegalArgumentException("Reservation end date cannot be before begin date");
        }

        ReservationDateTime now = ReservationDateTime.now();
        if (reservation.getReservationBeginDate().getValue().isBefore(now.getValue().toLocalDate())) {
            throw new IllegalArgumentException("Cannot reserve a room in the past");
        }
        reservation.setReservationDateTime(now);

        if (isReservationForRoomInInterval(reservation.getRoom(), reservation.getReservationBeginDate(), reservation.getReservationEndDate())) {
            throw new IllegalArgumentException("There is already a reservation for the selected room in the given interval");
        }

        reservationRepository.save(reservation);
    }

    public boolean isReservationForRoomInInterval(Room room, ReservationBeginDate reservationBeginDate, ReservationEndDate reservationEndDate) {
        return reservationRepository.existsReservationForRoomInInterval(
                room,
                reservationBeginDate.getValue(),
                reservationEndDate.getValue());
    }
}
