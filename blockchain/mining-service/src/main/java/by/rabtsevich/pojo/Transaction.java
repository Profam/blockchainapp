package by.rabtsevich.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "transaction")
public class Transaction {
    @Id
    @GeneratedValue(generator = "system-uuid")
    @GenericGenerator(name = "system-uuid", strategy = "uuid2")
    private String id;
    @NotBlank
    private String walletId; //Senders address/public key.
    @NotBlank
    private String receiverWalletId; //Recipients address/public key.
    @NotBlank
    private String senderSecretKey; //Senders secret key.
    @NotNull
    @Min(1)
    @Max(99)
    private int value; //Contains the amount we wish to send to the recipient.

    //3 statuses "pending", "accepted", "genesis"
    private String transactionStatus;
}
