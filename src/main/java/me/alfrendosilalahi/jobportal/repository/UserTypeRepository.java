package me.alfrendosilalahi.jobportal.repository;

import me.alfrendosilalahi.jobportal.model.UserType;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserTypeRepository extends JpaRepository<UserType, Integer> {
}
