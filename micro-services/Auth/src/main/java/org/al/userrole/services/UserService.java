package org.al.userrole.services;

import lombok.RequiredArgsConstructor;
import org.al.userrole.entities.User;
import org.al.userrole.repositories.UserRepository;
import org.al.userrole.servicesinterface.IUserService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService implements IUserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;


    @Override
    @Transactional
    public User createUser(String email, String password) {
        String hashedPassword = passwordEncoder.encode(password);
        User user = User.builder()
                .email(email)
                .password(hashedPassword)
                .build();
        return userRepository.save(user);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<User> getUserByEmail(String email) {
        return userRepository.findByEmail(email);
    }


    @Override
    @Transactional
    public void updateUser(User user) {
        userRepository.save(user);
    }
}