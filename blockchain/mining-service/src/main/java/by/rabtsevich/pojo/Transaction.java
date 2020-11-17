package by.rabtsevich.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

//transaction - операция по переводу одного или нескольких блоков с одного кошелька на другой
@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "transaction")
public class Transaction {
    @Id
    @GeneratedValue(generator = "system-uuid")
    @GenericGenerator(name = "system-uuid", strategy = "uuid2")
    private String id; //Contains a hash of transaction
    @Column(name = "walletId")
    private String senderWalletId; //Senders address/public key.
    @Column(name = "receiverWalletId")
    private String receiverWalletId; //Recipients address/public key.
    @Column(name = "senderSecretKey")
    private String senderSecretkey; //Senders secret key.
    @Column(name = "value")
    private int value; //Contains the amount we wish to send to the recipient.
    @Column(name = "status")
    private String transactionStatus;
}
