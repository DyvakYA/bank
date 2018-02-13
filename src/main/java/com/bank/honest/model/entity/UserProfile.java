package com.bank.honest.model.entity;

import com.bank.honest.model.dto.UserProfileDTO;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.Email;

/**
 * Created by User on 2/10/2018.
 */

@Entity
@Table(name = "user_profile")
@Builder
@AllArgsConstructor(access = AccessLevel.PACKAGE)
@NoArgsConstructor(access = AccessLevel.PUBLIC)
@Setter
@Getter
@EqualsAndHashCode
@ToString(exclude = "id")
public class UserProfile {

    @Id
    @GeneratedValue
    private long id;

    @Column(name="user_email")
    @Email
    private String email;

    @Column(name="user_firstname")
    private String firstName;

    @Column(name="user_lastname")
    private String lastName;

    public UserProfileDTO toDTO() {
        return UserProfileDTO.builder()
                .email(email)
                .firstName(firstName)
                .lastName(lastName)
                .build();
    }

    public static UserProfile fromDTO(UserProfileDTO dto) {
        return UserProfile.builder()
                .email(dto.getEmail())
                .firstName(dto.getFirstName())
                .lastName(dto.getLastName())
                .build();
    }
}
