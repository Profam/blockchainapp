package by.rabtsevich.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "transaction")
public class Transaction {
    @Id
    @GeneratedValue(generator = "system-uuid")
    @GenericGenerator(name = "system-uuid", strategy = "uuid2")
    private String id;
    @NotBlank
    @Column(name = "wallet_id")
    private String walletId;
    @NotBlank
    @Column(name = "receiver_wallet_id")
    private String receiverWalletId;
    @NotBlank
    @Column(name = "sender_secret_key")
    private String senderSecretKey;
    @NotNull
    @Min(1)
    @Max(99)
    @Column(name = "value")
    private int value;
    //3 statuses "pending", "accepted", "genesis"
    @Column(name = "transaction_status")
    private String transactionStatus;
}
