package com.bank.honest.model.entity;

import com.bank.honest.model.dto.TransactionDTO;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

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
@JsonIgnoreProperties({"account"})
public class Transaction {

    @Id
    @GeneratedValue(strategy = GenerationType.TABLE)
    private long id;

    @Temporal(TemporalType.TIMESTAMP)
    @DateTimeFormat(pattern = "yyyy-MM-dd hh:mm:ss")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd@HH:mm:ss.SSSZ")
    @Column(name = "DATETIME_FIELD", updatable = false)
    private Date date;

    @Column(name = "account_transaction_status")
    private TransactionStatus status;

    @Column(name="account_transaction_number")
    private String number;

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
                .status(dto.getStatus())
                .type(dto.getType())
                .amount(dto.getAmount())
                .currency(dto.getCurrency())
                .build();
    }
}
