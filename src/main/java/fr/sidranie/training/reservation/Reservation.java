package fr.sidranie.training.reservation;

import java.util.Iterator;
import java.util.Set;

import fr.sidranie.training.reservation.data.reservationDateTime.ReservationDateTime;
import fr.sidranie.training.reservation.data.reservationDateTime.ReservationDateTimeConverter;
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

    public Reservation(User user, Set<Room> rooms) {
        this.user = user;
        this.rooms = rooms;
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
