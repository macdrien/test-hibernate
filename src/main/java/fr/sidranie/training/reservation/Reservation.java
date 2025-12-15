package fr.sidranie.training.reservation;

import fr.sidranie.training.reservation.data.reservationDateTime.ReservationDateTime;
import fr.sidranie.training.reservation.data.reservationDateTime.ReservationDateTimeConverter;

import jakarta.persistence.Column;
import jakarta.persistence.Convert;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "reservations")
public class Reservation {
    @Id
    @GeneratedValue
    private Long id;

    @Column(name = "reservation_date_time", nullable = false)
    @Convert(converter = ReservationDateTimeConverter.class)
    private ReservationDateTime reservationDateTime;
    
    public Reservation() {
    }
    
    public Reservation(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public ReservationDateTime getReservationDateTime() {
        return reservationDateTime;
    }

    public void setReservationDateTime(ReservationDateTime reservationDateTime) {
        this.reservationDateTime = reservationDateTime;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Reservation{");
        sb.append("id=").append(id);
        sb.append(", reservationDateTime=").append(reservationDateTime);
        sb.append('}');
        return sb.toString();
    }
}
