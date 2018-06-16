package com.bank.honest.model.entity;

import com.bank.honest.model.dto.AccountDTO;
import com.bank.honest.model.entity.enums.Currency;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by User on 2/10/2018.
 */

@Entity
@Table(name = "user_account")
@Builder
@AllArgsConstructor(access = AccessLevel.PACKAGE)
@NoArgsConstructor(access = AccessLevel.PACKAGE)
@Setter
@Getter
@EqualsAndHashCode
@ToString(exclude = {"id", "transactions", "wallets", "customUser"})
@JsonIgnoreProperties({"transactions", "wallets", "customUser"})
public class Account {

    @Id
    @GeneratedValue(strategy = GenerationType.TABLE)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "user_account_number", nullable = false)
    private String number;

    @Column(name = "user_account_amount")
    private Long amount;

    @Column(name = "user_account_currency", nullable = false)
    private Currency currency;

    @Column(name = "account_is_blocked")
    private boolean isBlocked;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private CustomUser customUser;

    @OneToMany(mappedBy = "account", cascade = {CascadeType.PERSIST, CascadeType.REFRESH})
    private List<Transaction> transactions = new ArrayList<>();

    @OneToMany(mappedBy = "account", cascade = CascadeType.ALL)
    private List<Wallet> wallets = new ArrayList<>();

    public AccountDTO toDTO() {
        return AccountDTO.builder()
                .id(id)
                .number(number)
                .amount(amount)
                .currency(currency)
                .customUser(customUser)
                .isBlocked(isBlocked)
                .build();
    }

    public static Account fromDTO(AccountDTO dto) {
        return Account.builder()
                .id(dto.getId())
                .number(dto.getNumber())
                .amount(dto.getAmount())
                .customUser(dto.getCustomUser())
                .build();
    }
}
