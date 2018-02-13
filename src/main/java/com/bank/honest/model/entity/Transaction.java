package com.bank.honest.model.entity;

import com.bank.honest.model.dto.TransactionDTO;
import lombok.*;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by User on 2/12/2018.
 */
@Entity
@Table(name = "account_transaction")
@Builder
@AllArgsConstructor(access = AccessLevel.PACKAGE)
@NoArgsConstructor(access = AccessLevel.PACKAGE)
@Setter
@Getter
@EqualsAndHashCode
@ToString(exclude = "id")
public class Transaction {

    @Id
    @GeneratedValue
    private long id;

    @Column(name = "account_transaction_date", insertable = false, updatable = false)
    @Temporal(TemporalType.DATE)
    private Date date = new Date();

    @Column(name = "account_transaction_status")
    private boolean status;

    @Column(name = "account_transaction_type")
    private TransactionType type;

    @Column(name = "account_transaction_amount")
    private long amount;

    @Column(name = "account_transaction_currency")
    private Currency currency;

    @ManyToOne
    @JoinColumn(name="account_id")
    private Account account;

    public TransactionDTO toDTO() {
        return TransactionDTO.builder()
                .date(date)
                .status(status)
                .type(type)
                .amount(amount)
                .currency(currency)
                .build();
    }

    public static Transaction fromDTO(TransactionDTO dto) {
        return Transaction.builder()
                .date(dto.getDate())
                .status(dto.isStatus())
                .type(dto.getType())
                .amount(dto.getAmount())
                .currency(dto.getCurrency())
                .build();
    }
}
