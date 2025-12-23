package fr.sidranie.training.reservation;

import org.springframework.stereotype.Service;

import fr.sidranie.training.reservation.data.reservationDateTime.ReservationDateTime;

@Service
public class ReservationService {
    private final ReservationRepository reservationRepository;

    public ReservationService(ReservationRepository reservationRepository) {
        this.reservationRepository = reservationRepository;
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

        if (reservationRepository.existsReservationForRoomInInterval(
                reservation.getRoom(),
                reservation.getReservationBeginDate().getValue(),
                reservation.getReservationEndDate().getValue())) {
            throw new IllegalArgumentException("There is already a reservation for the selected room in the given interval");
        }

        reservationRepository.save(reservation);
    }
}
