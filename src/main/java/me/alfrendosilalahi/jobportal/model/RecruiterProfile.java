package me.alfrendosilalahi.jobportal.model;

import jakarta.persistence.*;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@ToString
@Entity
@Table(name = "recruiter_profile")
public class RecruiterProfile {

    @Id
    @Column(name = "user_account_id")
    private Integer userAccountId;

    @ToString.Exclude
    @OneToOne
    @JoinColumn(name = "user_account_id")
    @MapsId
    private User user;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @Column(name = "city")
    private String city;

    @Column(name = "state")
    private String state;

    @Column(name = "country")
    private String country;

    @Column(name = "company")
    private String company;

    @Column(name = "profile_photo", length = 64)
    private String profilePhoto;

    public RecruiterProfile(User user) {
        this.user = user;
    }

}
