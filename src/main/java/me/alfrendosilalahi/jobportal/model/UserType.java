package me.alfrendosilalahi.jobportal.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@ToString
@Entity
@Table(name = "users_type")
public class UserType {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_type_id")
    private Integer userTypeId;

    @Column(name = "user_type_name")
    private String userTypeName;

    @ToString.Exclude
    @OneToMany(
            targetEntity = User.class,
            mappedBy = "userType",
            cascade = CascadeType.ALL
    )
    private List<User> users;

}
