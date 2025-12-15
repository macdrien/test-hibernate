package fr.sidranie.training.user;

import java.util.Collection;
import java.util.Set;

import fr.sidranie.training.user.data.username.Username;

public class UserService {
    private final UserRepository userRepository;

    public UserService() {
        this.userRepository = new UserRepository();
    }
    
    public Collection<User> getAllUsers() {
        return userRepository.findAll();
    }
    
    public User getUserById(Long id) {
        return userRepository.findById(id);
    }

    public User getUserByUsername(Username username) {
        return userRepository.findByUsername(username);
    }
    
    public void registerUser(User user) {
        if (user.getReservations() == null) {
            user.setReservations(Set.of());
        }
        userRepository.save(user);
    }
}
