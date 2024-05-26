package me.alfrendosilalahi.jobportal.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Entity
@ToString
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private Integer userId;

    @Column(name = "email")
    private String email;

    @ToString.Exclude
    @Column(name = "password")
    @NotEmpty
    private String password;

    @Column(name = "is_active")
    private Boolean isActive;

    @Column(name = "registration_date")
    @DateTimeFormat(pattern = "dd-MM-yyyy")
    private Date registrationDate;

    @ToString.Exclude
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "user_type_id", referencedColumnName = "user_type_id")
    private UserType userType;

}
