package fr.sidranie.training.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Convert;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import fr.sidranie.training.data.password.Password;
import fr.sidranie.training.data.password.PasswordConverter;
import fr.sidranie.training.data.username.Username;
import fr.sidranie.training.data.username.UsernameConverter;

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

    public User() {
    }

    public User(Username username, Password password) {
        this.username = username;
        this.password = password;
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

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("User{");
        sb.append("id=").append(id);
        sb.append(", username=").append(username.toString());
        sb.append(", password=").append(password.toString());
        sb.append('}');
        return sb.toString();
    }
}
