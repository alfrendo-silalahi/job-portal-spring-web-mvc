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
@Table(name = "job_seeker_profile")
public class JobSeekerProfile {

    @Id
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

    @Column(name = "work_authorization")
    private String workAuthorization;

    @Column(name = "employment_type")
    private String employmentType;

    @Column(name = "resume")
    private String resume;

    @Column(name = "profile_photo", length = 64)
    private String profilePhoto;

    @ToString.Exclude
    @OneToMany(targetEntity = Skill.class, cascade = CascadeType.ALL, mappedBy = "jobSeekerProfile")
    private List<Skill> skills;

    public JobSeekerProfile(User user) {
        this.user = user;
    }

}
