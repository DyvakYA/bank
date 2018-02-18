package com.bank.honest.model.entity;

import com.bank.honest.model.dto.WalletDTO;
import lombok.*;

import javax.persistence.*;

/**
 * Created by User on 2/12/2018.
 */
@Entity
@Table(name = "user_wallet")
@Builder
@AllArgsConstructor(access = AccessLevel.PACKAGE)
@NoArgsConstructor(access = AccessLevel.PACKAGE)
@Setter
@Getter
@EqualsAndHashCode
@ToString(exclude = "id")
public class Wallet {

    @Id
    @GeneratedValue
    private long id;

    @Column(name = "user_wallet_name")
    private String name;

    @Column(name = "user_wallet_number")
    private String number;

    @Column(name = "user_wallet_expired")
    private String expired;

    @Column(name = "user_wallet_status")
    private WalletStatus status;
    @ManyToOne
    @JoinColumn(name="account_id")
    private Account account;

    public WalletDTO toDTO() {
        return WalletDTO.builder()
                .name(name)
                .number(number)
                .expired(expired)
                .status(status)
                .build();
    }

    public static Wallet fromDTO(WalletDTO dto) {
        return Wallet.builder()
                .name(dto.getName())
                .number(dto.getNumber())
                .expired(dto.getExpired())
                .status(dto.getStatus())
                .build();
    }
}
