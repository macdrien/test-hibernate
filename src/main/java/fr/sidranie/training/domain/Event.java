package fr.sidranie.training.domain;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "events")
public class Event {

    @Id
    @GeneratedValue
    private Long id;

    @Column
    private String title;

    @Column
    private LocalDateTime date;
    
    public Event() {
    }

    public Event(String title) {
        this(title, LocalDateTime.now());
    }

    public Event(String title, LocalDateTime date) {
        this.title = title;
        this.date = date;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Event{");
        sb.append("id=").append(id);
        sb.append(", title='").append(title);
        sb.append("', date=").append(date);
        sb.append('}');
        return sb.toString();
    }

}
