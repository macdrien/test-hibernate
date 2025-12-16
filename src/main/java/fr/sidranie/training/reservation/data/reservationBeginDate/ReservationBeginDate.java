package fr.sidranie.training.reservation.data.reservationBeginDate;

import java.time.LocalDate;
import java.util.Objects;

public class ReservationBeginDate {
    private LocalDate value;

    public ReservationBeginDate(LocalDate value) {
        this.value = value;
    }

    public LocalDate getValue() {
        return value;
    }

    public void setValue(LocalDate value) {
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
        final ReservationBeginDate other = (ReservationBeginDate) obj;
        return Objects.equals(this.value, other.getValue());
    }

    @Override
    public String toString() {
        return new StringBuilder()
                .append("ReservationBeginDate{")
                .append(value)
                .append('}')
                .toString();
    }
}
