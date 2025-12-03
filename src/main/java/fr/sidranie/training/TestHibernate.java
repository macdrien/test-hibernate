package fr.sidranie.training;

import org.hibernate.metamodel.internal.FullNameImplicitDiscriminatorStrategy;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import fr.sidranie.training.user.User;
import fr.sidranie.training.user.UserRepository;
import fr.sidranie.training.user.data.password.Password;
import fr.sidranie.training.user.data.username.Username;

public class TestHibernate {
    private static final Logger LOGGER = LoggerFactory.getLogger(TestHibernate.class);

    public static void main(String[] args) {
        UserRepository userRepository = new UserRepository();

        User user = new User(
            new Username("john_doe"),
            new Password("securePassword123")
        );
        userRepository.save(user);

        userRepository.findAll().forEach(result -> LOGGER.debug("{}", result));

        User foundUser = userRepository.findById(1L);
        if (foundUser != null) {
            LOGGER.info("User found {}", foundUser.toString());
        } else {
            LOGGER.warn("User not found");
        }
    }
}
