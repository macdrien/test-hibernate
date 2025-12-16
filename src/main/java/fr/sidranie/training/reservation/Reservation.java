package fr.sidranie.training.reservation;

import java.util.Iterator;
import java.util.Set;

import fr.sidranie.training.reservation.data.reservationBeginDate.ReservationBeginDate;
import fr.sidranie.training.reservation.data.reservationBeginDate.ReservationBeginDateConverter;
import fr.sidranie.training.reservation.data.reservationDateTime.ReservationDateTime;
import fr.sidranie.training.reservation.data.reservationDateTime.ReservationDateTimeConverter;
import fr.sidranie.training.reservation.data.reservationEndDate.ReservationEndDate;
import fr.sidranie.training.reservation.data.reservationEndDate.ReservationEndDateConverter;
import fr.sidranie.training.room.Room;
import fr.sidranie.training.user.User;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Convert;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
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

    @Column(name = "reservation_begin_date", nullable = false)
    @Convert(converter = ReservationBeginDateConverter.class)
    private ReservationBeginDate reservationBeginDate;

    @Column(name = "reservation_end_date", nullable = false)
    @Convert(converter = ReservationEndDateConverter.class)
    private ReservationEndDate reservationEndDate;

    @ManyToOne(fetch = FetchType.LAZY,
            cascade = CascadeType.ALL)
    private User user;

    @ManyToMany(fetch = FetchType.LAZY,
            cascade = CascadeType.ALL)
    private Set<Room> rooms;
    
    public Reservation() {
    }
    
    public Reservation(Long id) {
        this.id = id;
    }

    public Reservation(User user,
            Set<Room> rooms,
            ReservationBeginDate reservationBeginDate,
            ReservationEndDate reservationEndDate) {
        this.user = user;
        this.rooms = rooms;
        this.reservationBeginDate = reservationBeginDate;
        this.reservationEndDate = reservationEndDate;
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

    public ReservationBeginDate getReservationBeginDate() {
        return reservationBeginDate;
    }

    public void setReservationBeginDate(ReservationBeginDate reservationBeginDateTime) {
        this.reservationBeginDate = reservationBeginDateTime;
    }

    public ReservationEndDate getReservationEndDate() {
        return reservationEndDate;
    }

    public void setReservationEndDate(ReservationEndDate reservationEndDate) {
        this.reservationEndDate = reservationEndDate;
    }
    
    public User getUser() {
        return user;
    }
    
    public void setUser(User user) {
        this.user = user;
    }
    
    public Set<Room> getRooms() {
        return rooms;
    }
    
    public void setRooms(Set<Room> rooms) {
        this.rooms = rooms;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Reservation{");
        sb.append("id=").append(id);
        sb.append(", reservationDateTime=").append(reservationDateTime);
        sb.append(", reservationBeginDate=").append(reservationBeginDate);
        sb.append(", reservationEndDate=").append(reservationEndDate);
        sb.append(", user=").append(user);

        sb.append(", rooms=[");
        Iterator<Room> roomIterator = rooms.iterator();
        while (roomIterator.hasNext()) {
            Room room = roomIterator.next();
            sb.append(room.toString());
            if (roomIterator.hasNext()) {
                sb.append(", ");
            }
        }

        sb.append("]}");
        return sb.toString();
    }
}
