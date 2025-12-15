package fr.sidranie.training.reservation.data.reservationDateTime;

import java.time.LocalDateTime;

public class ReservationDateTime {
    private LocalDateTime value;

    public ReservationDateTime() {
    }
    
    public ReservationDateTime(LocalDateTime value) {
        this.value = value;
    }

    public static ReservationDateTime now() {
        return new ReservationDateTime(LocalDateTime.now());
    }

    public LocalDateTime getValue() {
        return value;
    }

    public void setValue(LocalDateTime value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return new StringBuilder()
            .append("ReservationDateTime{")
            .append(value)
            .append('}')
            .toString();
    }
}
