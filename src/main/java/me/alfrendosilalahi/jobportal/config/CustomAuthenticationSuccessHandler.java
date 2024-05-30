package me.alfrendosilalahi.jobportal.config;

import java.io.IOException;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class CustomAuthenticationSuccessHandler implements AuthenticationSuccessHandler {

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
            Authentication authentication) throws IOException, ServletException {
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        String username = userDetails.getUsername();
        log.info("username :: {}", username);   
        boolean hasJobSeekerRole = authentication
                .getAuthorities()
                .stream()
                .anyMatch(r -> r.getAuthority().equals("Job Seeker")
        );
        boolean hasJobRecruiterRole = authentication
                .getAuthorities()
                .stream()
                .anyMatch(r -> r.getAuthority().equals("Recruiter")
        );

        if (hasJobSeekerRole || hasJobRecruiterRole) {
            response.sendRedirect("/dashboard");
        }
    }

}
