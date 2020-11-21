package by.rabtsevich.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "blocks")
public class Block {

    @Id
    @Column(name = "id")
    private long blockId;
    @Column(name = "time_stamp")
    private long timeStamp;

    @Column(name = "previous_hash")
    private String previousHash;

    @Column(name = "hash")
    private String hash;

    @Column(name = "transaction")
    private String hashOfTransactionList;

    @Column(name = "nonce")
    private int nonce;
}

