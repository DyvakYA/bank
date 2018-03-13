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
    @Column(name = "id", nullable = false)
    private long id;

    @Temporal(TemporalType.TIMESTAMP)
    @DateTimeFormat(pattern = "yyyy-MM-dd hh:mm:ss")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd@HH:mm:ss.SSSZ")
    @Column(name = "DATETIME_FIELD", nullable = false, updatable = false)
    private Date date;

    @Column(name = "transaction_number", nullable = false)
    private String number;

    @Column(name = "transaction_source_name", nullable = false)
    private String sourceName;

    @Column(name = "transaction_destination_name", nullable = false)
    private String destinationName;

    @Column(name = "account_transaction_amount")
    private Long sum;

    @Column(name = "account_transaction_currency", nullable = false)
    private Currency currency;

    @Column(name = "account_transaction_status")
    private TransactionStatus status;

    @ManyToOne
    @JoinColumn(name = "account_id")
    private Account account;

    public TransactionDTO toDTO() {
        return TransactionDTO.builder()
                .date(date)
                .number(number)
                .sourceName(sourceName)
                .destinationName(destinationName)
                .sum(sum)
                .currency(currency)
                .status(status)
                .build();
    }

    public static Transaction fromDTO(TransactionDTO dto) {
        return Transaction.builder()
                .date(dto.getDate())
                .number(dto.getNumber())
                .sourceName(dto.getSourceName())
                .destinationName(dto.getDestinationName())
                .sum(dto.getSum())
                .currency(dto.getCurrency())
                .status(dto.getStatus())
                .build();
    }
}
