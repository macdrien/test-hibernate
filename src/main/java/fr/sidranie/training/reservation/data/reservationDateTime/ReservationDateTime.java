package fr.sidranie.training.reservation.data.reservationDateTime;

import java.time.LocalDateTime;
import java.util.Objects;

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
    public int hashCode() {
        return Objects.hash(value);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final ReservationDateTime other = (ReservationDateTime) obj;
        return Objects.equals(this.value, other.getValue());
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
