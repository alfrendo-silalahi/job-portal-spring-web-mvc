package me.alfrendosilalahi.jobportal.repository;

import me.alfrendosilalahi.jobportal.model.Skill;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SkillRepository extends JpaRepository<Skill, Integer> {
}
