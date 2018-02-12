package com.bank.honest.model.entity;

import com.bank.honest.model.dto.AccountDTO;
import lombok.*;

import javax.persistence.*;

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
public class Account {

    @Id
    @GeneratedValue
    private long id;

    @Column(name="user_account_number")
    private String number;

    @Column(name="user_account_amount")
    private long amount;

    @ManyToOne
    @JoinColumn(name="user_id")
    private CustomUser customUser;

    public Account(String accountNumber, long amount, CustomUser customUser) {
        this.number = accountNumber;
        this.amount = amount;
        this.customUser = customUser;
    }

    public AccountDTO toDTO() {
        return new AccountDTO(number, amount, null);
    }

    public static Account fromDTO(AccountDTO dto) {
        return new Account(dto.getAccountNumber(), dto.getAmount(), dto.getCustomUser());
    }
}
