package fr.sidranie.training.user;

import java.util.Set;

import fr.sidranie.training.reservation.Reservation;
import fr.sidranie.training.user.data.firstName.FirstName;
import fr.sidranie.training.user.data.firstName.FirstNameConverter;
import fr.sidranie.training.user.data.lastName.LastName;
import fr.sidranie.training.user.data.lastName.LastNameConverter;
import fr.sidranie.training.user.data.password.Password;
import fr.sidranie.training.user.data.password.PasswordConverter;
import fr.sidranie.training.user.data.username.Username;
import fr.sidranie.training.user.data.username.UsernameConverter;

import jakarta.persistence.Column;
import jakarta.persistence.Convert;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue
    private Long id;

    @Column(nullable = false, unique = true)
    @Convert(converter = UsernameConverter.class)
    private Username username;

    @Column(nullable = false)
    @Convert(converter = PasswordConverter.class)
    private Password password;

    @Column(nullable = false)
    @Convert(converter = FirstNameConverter.class)
    private FirstName firstName;

    @Column(nullable = false)
    @Convert(converter = LastNameConverter.class)
    private LastName lastName;

    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY)
    private Set<Reservation> reservations;

    public User() {
    }

    public User(Username username,
                Password password,
                FirstName firstName,
                LastName lastName) {
        this.username = username;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Username getUsername() {
        return username;
    }

    public void setUsername(Username username) {
        this.username = username;
    }

    public Password getPassword() {
        return password;
    }

    public void setPassword(Password password) {
        this.password = password;
    }

    public FirstName getFirstName() {
        return firstName;
    }

    public void setFirstName(FirstName firstName) {
        this.firstName = firstName;
    }

    public LastName getLastName() {
        return lastName;
    }

    public void setLastName(LastName lastName) {
        this.lastName = lastName;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("User{");
        sb.append("id=").append(id);
        sb.append(", username=").append(username.toString());
        sb.append(", password=").append(password.toString());
        sb.append(", firstName=").append(firstName.toString());
        sb.append(", lastName=").append(lastName.toString());
        sb.append('}');
        return sb.toString();
    }

    public Set<Reservation> getReservations() {
        return reservations;
    }

    public void setReservations(Set<Reservation> reservations) {
        this.reservations = reservations;
    }
}
