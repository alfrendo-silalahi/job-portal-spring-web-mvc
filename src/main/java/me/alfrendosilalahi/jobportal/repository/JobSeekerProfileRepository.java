package me.alfrendosilalahi.jobportal.repository;

import me.alfrendosilalahi.jobportal.model.JobSeekerProfile;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JobSeekerProfileRepository extends JpaRepository<JobSeekerProfile, Integer> {
}
