package me.alfrendosilalahi.jobportal.service;

import lombok.RequiredArgsConstructor;
import me.alfrendosilalahi.jobportal.model.JobSeekerProfile;
import me.alfrendosilalahi.jobportal.model.RecruiterProfile;
import me.alfrendosilalahi.jobportal.model.User;
import me.alfrendosilalahi.jobportal.repository.JobSeekerProfileRepository;
import me.alfrendosilalahi.jobportal.repository.RecruiterProfileRepository;
import me.alfrendosilalahi.jobportal.repository.UserRepository;

import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    private final JobSeekerProfileRepository jobSeekerProfileRepository;

    private final RecruiterProfileRepository recruiterProfileRepository;

    private final PasswordEncoder passwordEncoder;

    @Transactional
    public void createUser(User user) {
        user.setIsActive(true);
        user.setRegistrationDate(new Date(System.currentTimeMillis()));
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        User newUser = userRepository.save(user);
        int userTypeId = newUser.getUserType().getUserTypeId();
        if (userTypeId == 1) {
            recruiterProfileRepository.save(new RecruiterProfile(newUser));
        } else {
            jobSeekerProfileRepository.save(new JobSeekerProfile(newUser));
        }
    }

    public Object getCurrentUserProfile() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (!(authentication instanceof AnonymousAuthenticationToken)) {
            String username = authentication.getName();
            User user = userRepository.findByEmail(username)
                    .orElseThrow(() -> new UsernameNotFoundException("could not found user"));
            int userId = user.getUserId();
            if (authentication.getAuthorities().contains(new SimpleGrantedAuthority("Recruiter"))) {
                return recruiterProfileRepository.findById(userId).orElse(new RecruiterProfile());
            } else {
                return jobSeekerProfileRepository.findById(userId).orElse(new JobSeekerProfile());
            }
        }
        return null;
    }

}