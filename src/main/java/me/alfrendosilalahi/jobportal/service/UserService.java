package me.alfrendosilalahi.jobportal.service;

import lombok.RequiredArgsConstructor;
import me.alfrendosilalahi.jobportal.model.JobSeekerProfile;
import me.alfrendosilalahi.jobportal.model.RecruiterProfile;
import me.alfrendosilalahi.jobportal.model.User;
import me.alfrendosilalahi.jobportal.repository.JobSeekerProfileRepository;
import me.alfrendosilalahi.jobportal.repository.RecruiterProfileRepository;
import me.alfrendosilalahi.jobportal.repository.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    private final JobSeekerProfileRepository jobSeekerProfileRepository;

    private final RecruiterProfileRepository recruiterProfileRepository;

    @Transactional
    public void createUser(User user) {
        user.setIsActive(true);
        user.setRegistrationDate(new Date(System.currentTimeMillis()));
        User newUser = userRepository.save(user);
        int userTypeId = newUser.getUserType().getUserTypeId();
        if (userTypeId == 1) {
            recruiterProfileRepository.save(new RecruiterProfile(newUser));
        } else {
            jobSeekerProfileRepository.save(new JobSeekerProfile(newUser));
        }
    }

}