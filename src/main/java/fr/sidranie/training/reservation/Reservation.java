package fr.sidranie.training.reservation;

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

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Reservation{");
        sb.append("id=").append(id);
        sb.append('}');
        return sb.toString();
    }
}
