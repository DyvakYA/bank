package com.bank.honest.model.entity;

import com.bank.honest.model.dto.UserDTO;
import lombok.*;

import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

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
@ToString(exclude = "id")
public class CustomUser {
    @Id
    @GeneratedValue(strategy = GenerationType.TABLE)
    private long id;

    @Column(name="user_phone")
    @NotNull(message = "Not null")
    private String phone;

    @Column(name="user_password")
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
    private UserProfile userProfile;

    @PreRemove
    public void nullableContacts(){
        accounts.forEach(c->c.setCustomUser(null));
    }

    public UserDTO toDTO() {
        return UserDTO.builder()
                .phone(phone)
                .password(password)
                .role(role)
                .accounts(accounts
                        .stream()
                        .map(p->p.getNumber())
                        .collect(Collectors.toList()))
                .build();
    }

    public static CustomUser fromDTO(UserDTO dto) {
        return CustomUser.builder()
                .phone(dto.getPhone())
                .password(dto.getPassword())
                .role(dto.getRole())
                //.accounts(dto.getAccounts().stream().map(p->accounts.))
                .build();
    }
}
