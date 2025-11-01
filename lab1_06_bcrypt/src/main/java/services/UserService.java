package services;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import model.User;
import repository.UserRepository;

public class UserService {

    private UserRepository userRepository = new UserRepository();

    public void addUser(User user) throws Exception {
        BCryptPasswordEncoder bCrypt = new BCryptPasswordEncoder();
        user.setHashPassword(
                bCrypt.encode(user.getHashPassword()));

        userRepository.addUser(user);
    }

}