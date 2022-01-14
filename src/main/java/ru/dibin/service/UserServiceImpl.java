package ru.dibin.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.dibin.persist.User;
import ru.dibin.persist.UserRepository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    private final PasswordEncoder passwordEncoder;

    @Autowired
    public UserServiceImpl(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public List<UserRepr> findAll() {
        return userRepository.findAll().stream()
                .map(UserRepr::new)
                .collect(Collectors.toList());
    }

    @Transactional
    @Override
    public Optional<UserRepr> findById(long id) {
        return userRepository.findById(id)
                .map(UserRepr::new);
    }

    @Transactional
    @Override
    public void save(UserRepr user) {
        User userToSave = new User(user);
        userToSave.setPassword(passwordEncoder.encode(userToSave.getPassword()));
        userRepository.save(userToSave);
        if (user.getId() == null) {
            user.setId(userToSave.getId());
        }
    }

    @Transactional
    @Override
    public void delete(long id) {
        userRepository.deleteById(id);
    }
}
