package by.rabtsevich.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "wallet")
public class Wallet {
    @Id
    @GeneratedValue(generator = "system-uuid")
    @GenericGenerator(name = "system-uuid", strategy = "uuid2")
    @Column(name = "wallet_id")
    private String walletId;
    @Column(name = "secret_key")
    private String secretKey;
    @Column(name = "wallet_name")
    private String walletName;
    @Column(name = "wallet_owner")
    private String walletOwner;
    @Column(name = "balance")
    private int balance;
}
