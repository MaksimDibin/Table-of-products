package ru.dibin.service;

import java.util.List;
import java.util.Optional;

public interface UserService {

    List<UserRepr> findAll();

    Optional<UserRepr> findById(long id);

    void save(UserRepr user);

    void delete(long id);
}
