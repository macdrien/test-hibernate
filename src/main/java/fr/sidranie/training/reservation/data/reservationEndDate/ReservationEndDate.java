package fr.sidranie.training.reservation.data.reservationEndDate;

import java.time.LocalDate;
import java.util.Objects;

import fr.sidranie.training.reservation.data.reservationBeginDate.ReservationBeginDate;

public class ReservationEndDate {
    private LocalDate value;

    public ReservationEndDate(LocalDate value) {
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
        final ReservationEndDate other = (ReservationEndDate) obj;
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

    public int compareTo(Object other) {
        if (this == other) {
            return 0;
        }
        switch (other) {
            case null -> {
                return 1;
            }
            case ReservationBeginDate reservationBeginDate -> {
                return this.value.compareTo(reservationBeginDate.getValue());
            }
            case ReservationEndDate reservationEndDate -> {
                return this.value.compareTo(reservationEndDate.getValue());
            }
            case LocalDate localDate -> {
                return this.value.compareTo(localDate);
            }
            default -> throw new IllegalArgumentException("Cannot compare ReservationEndDate with " + other.getClass().getName());
        }
    }
}
