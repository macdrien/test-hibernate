package fr.sidranie.training.reservation;

import java.util.List;

public class ReservationService {
    private final ReservationRepository reservationRepository;

    public ReservationService() {
        this.reservationRepository = new ReservationRepository();
    }

    public List<Reservation> getAllReservations() {
        return reservationRepository.findAll();
    }

    public void createReservation(Reservation reservation) {
        reservationRepository.save(reservation);
    }

}
