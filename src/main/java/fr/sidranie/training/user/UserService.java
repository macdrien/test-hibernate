package fr.sidranie.training.user;

import java.util.Set;

import org.springframework.stereotype.Service;

@Service
public class UserService {
    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public void registerUser(User user) {
        if (user.getReservations() == null) {
            user.setReservations(Set.of());
        }
        userRepository.save(user);
    }
}
