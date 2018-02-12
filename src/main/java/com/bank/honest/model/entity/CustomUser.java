package com.bank.honest.model.entity;

import com.bank.honest.model.dto.UserDTO;
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
@Table(name = "user")
@Builder
@AllArgsConstructor(access = AccessLevel.PACKAGE)
@NoArgsConstructor(access = AccessLevel.PUBLIC)
@Setter
@Getter
@EqualsAndHashCode
@ToString(exclude = "id")
public class CustomUser {
    @Id
    @GeneratedValue
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

    @PreRemove
    public void nullableContacts(){
        accounts.forEach(c->c.setCustomUser(null));
    }

    @OneToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="user_profile_id")
    private UserProfile userProfile;

    public CustomUser(String phone, String password, UserRole role, List<Account> accounts) {
        this.phone = phone;
        this.password = password;
        this.role = role;
        this.accounts = accounts;
    }

    public UserDTO toDTO() {
        return new UserDTO(phone, password, role, accounts);
    }

    public static CustomUser fromDTO(UserDTO dto) {
        return new CustomUser(dto.getPhone(), dto.getPassword(), dto.getRole(), dto.getAccounts());
    }
}
