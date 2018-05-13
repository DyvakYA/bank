package com.bank.honest.model.entity;

import com.bank.honest.model.dto.WalletDTO;
import com.bank.honest.model.entity.enums.WalletStatus;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
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
@JsonIgnoreProperties({"account"})
public class Wallet {

    @Id
    @GeneratedValue(strategy = GenerationType.TABLE)
    @Column(name = "id", nullable = false)
    private long id;

    @Column(name = "wallet_name", nullable = false)
    private String name;

    @Column(name = "wallet_number", nullable = false, unique = true)
    private String number;

    @Column(name = "wallet_limit")
    private Long limit;

    @Column(name = "wallet_expired")
    private String expired;

    @Column(name = "wallet_is_blocked")
    private boolean isBlocked;

    @Column(name = "wallet_sms_inform")
    private boolean smsInform;

    @Column(name = "user_wallet_status")
    private WalletStatus status;

    @ManyToOne
    @JoinColumn(name="account_id")
    private Account account;

    public WalletDTO toDTO() {
        return WalletDTO.builder()
                //.id(id)
                .name(name)
                .number(number)
                .expiration(expired)
                .build();
    }

    public static Wallet fromDTO(WalletDTO dto) {
        return Wallet.builder()
                .name(dto.getName())
                .number(dto.getNumber())
                .expired(dto.getExpiration())
                .build();
    }
}
