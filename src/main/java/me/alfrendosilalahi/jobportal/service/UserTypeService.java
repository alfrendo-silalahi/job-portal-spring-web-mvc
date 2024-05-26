package me.alfrendosilalahi.jobportal.service;

import lombok.RequiredArgsConstructor;
import me.alfrendosilalahi.jobportal.model.UserType;
import me.alfrendosilalahi.jobportal.repository.UserTypeRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserTypeService {

    private final UserTypeRepository userTypeRepository;

    public List<UserType> getAllUserTypes() {
        return userTypeRepository.findAll();
    }

}
