package com.bank.honest.model.entity;

import com.bank.honest.model.dto.UserDTO;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;

import javax.persistence.*;
import javax.validation.Valid;
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
@ToString(exclude = {"id","accounts","profile"})
@JsonIgnoreProperties({"accounts", "profile"})
public class CustomUser {
    @Id
    @GeneratedValue(strategy = GenerationType.TABLE)
    @Column(name = "id", nullable = false)
    private long id;

    @Column(name="user_phone", nullable = false)
    @NotNull(message = "Not null")
    private String phone;

    @Column(name="user_password", nullable = false)
    @NotNull(message = "not null")
    @Size(min = 5, max = 50)
    private String password;

    @Enumerated(EnumType.STRING)
    @Valid
    private UserRole role;

    @OneToMany(mappedBy="customUser", cascade = {CascadeType.PERSIST, CascadeType.REFRESH})
    private List<Account> accounts = new ArrayList<>();

    @OneToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="user_profile_id")
    private Profile profile;

    @PreRemove
    public void nullableContacts(){
        accounts.forEach(c->c.setCustomUser(null));
    }

    public UserDTO toDTO() {
        return UserDTO.builder()
                .id(id)
                .phone(phone)
                .password(password)
                .build();
    }

    public static CustomUser fromDTO(UserDTO dto) {
        return CustomUser.builder()
                .phone(dto.getPhone())
                .password(dto.getPassword())
                .build();
    }
}
