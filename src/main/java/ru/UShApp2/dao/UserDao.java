package ru.UShApp2.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.UShApp2.model.User;

public interface UserDao extends JpaRepository<User, Long> {
    User findByUsername(String username);
}
