package com.bank.honest.model.entity;

import com.bank.honest.model.dto.AccountDTO;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by User on 2/10/2018.
 */

@Entity
@Table(name="user_account")
@Builder
@AllArgsConstructor(access = AccessLevel.PACKAGE)
@NoArgsConstructor(access = AccessLevel.PACKAGE)
@Setter
@Getter
@EqualsAndHashCode
@ToString(exclude="id")
@JsonIgnoreProperties({"transactions", "wallet", "customUser"})
public class Account {

    @Id
    @GeneratedValue(strategy = GenerationType.TABLE)
    private Long id;

    @Column(name="user_account_number")
    private String number;

    @Column(name="user_account_amount")
    private long amount;

    @Column(name="user_account_currency")
    private Currency currency;

    @ManyToOne
    @JoinColumn(name="user_id")
    private CustomUser customUser;

    @OneToMany(mappedBy="account", cascade = {CascadeType.PERSIST, CascadeType.REFRESH})
    private List<Transaction> transactions = new ArrayList<>();

    @OneToMany(mappedBy="account", cascade = {CascadeType.PERSIST, CascadeType.REFRESH})
    private List<Wallet> wallet = new ArrayList<>();

    public AccountDTO toDTO() {
        return AccountDTO.builder()
                .number(number)
                .amount(amount)
                .currency(currency)
                .customUser(customUser)
                .build();
    }

    public static Account fromDTO(AccountDTO dto) {
        return Account.builder()
                .number(dto.getNumber())
                .amount(dto.getAmount())
                .customUser(dto.getCustomUser())
                .build();
    }
}
