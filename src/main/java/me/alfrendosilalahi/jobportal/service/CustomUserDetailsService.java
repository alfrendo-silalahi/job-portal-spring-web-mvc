package me.alfrendosilalahi.jobportal.service;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import me.alfrendosilalahi.jobportal.model.User;
import me.alfrendosilalahi.jobportal.repository.UserRepository;
import me.alfrendosilalahi.jobportal.util.CustomUserDetails;

@Service
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {

    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByEmail(username)
                .orElseThrow(() -> new UsernameNotFoundException("could not found user"));
        return new CustomUserDetails(user);
    }

}
