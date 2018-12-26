package com.bank.honest.model.entity;

import com.bank.honest.model.dto.UserDTO;
import com.bank.honest.model.entity.enums.UserRole;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;

import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by User on 2/10/2018.
 */
@Entity
@Table(name = "custom_user")
@Builder
@AllArgsConstructor(access = AccessLevel.PACKAGE)
@NoArgsConstructor(access = AccessLevel.PUBLIC)
@Setter
@Getter
@EqualsAndHashCode
@ToString(exclude = {"id", "accounts"})
@JsonIgnoreProperties({"accounts"})
public class CustomUser {
    @Id
    @GeneratedValue(strategy = GenerationType.TABLE)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "user_phone", nullable = false)
    @NotNull(message = "{user.phone.not_null}")
    @NotBlank(message = "{user.phone.not_empty}")
    private String phone;

    @Column(name = "user_password", nullable = false)
    @NotNull(message = "not null")
    @Size(min = 5, max = 50, message = "{user.password.between.invalid_range}")
    private String password;

    @Enumerated(EnumType.STRING)
    @Valid
    private UserRole role;

    @OneToMany(mappedBy = "customUser", cascade = {CascadeType.PERSIST, CascadeType.REFRESH})
    private List<Account> accounts = new ArrayList<>();

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "user_profile_id")
    private Profile profile;

    @PreRemove
    public void nullableContacts() {
        System.out.println("SET CUSTOM USER TO NULL");
        accounts.forEach(c -> c.setCustomUser(null));
    }

    public UserDTO toDTO() {
        return UserDTO.builder()
                .id(id)
                .phone(phone)
                .password(password)
                .role(role)
                .profile(profile)
                .build();
    }

    public static CustomUser fromDTO(UserDTO dto) {
        return CustomUser.builder()
                .phone(dto.getPhone())
                .password(dto.getPassword())
                .build();
    }
}
