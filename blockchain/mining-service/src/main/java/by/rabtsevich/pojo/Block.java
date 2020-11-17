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
    @Column(name = "blockId")
    private long blockId;
    @Column(name = "timeStamp")
    private long timeStamp;

    @Column(name = "previousHash")
    private String previousHash;

    @Column(name = "hash")
    private String hash;

    private String transaction;

    private int nonce;
}

