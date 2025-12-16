package fr.sidranie.training.reservation;

import java.util.List;

import fr.sidranie.training.reservation.data.reservationDateTime.ReservationDateTime;

public class ReservationService {
    private final ReservationRepository reservationRepository;

    public ReservationService() {
        this.reservationRepository = new ReservationRepository();
    }

    public List<Reservation> getAllReservations() {
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

        reservation.setReservationDateTime(ReservationDateTime.now());
        reservationRepository.save(reservation);
    }

}
