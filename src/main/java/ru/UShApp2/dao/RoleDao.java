package ru.UShApp2.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.UShApp2.model.Role;

public interface RoleDao extends JpaRepository<Role, Long> {
}
