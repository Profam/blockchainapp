package by.rabtsevich.pojo;

import by.rabtsevich.util.HashUtil;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.security.NoSuchAlgorithmException;
import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "blocks")
public class Block {

    @Id
    @Column(name = "time_stamp")
    private long timeStamp;

    @Column(name = "hash")
    private String hash;

    @Column(name = "previous_hash")
    private String previousHash;


    public Block(String previousHash) throws NoSuchAlgorithmException {
        timeStamp = new Date().getTime();
        this.hash = calculateHash();
        this.previousHash = previousHash;
    }

    private String calculateHash() throws NoSuchAlgorithmException {
        return hash = HashUtil.generate(previousHash + timeStamp);
    }

}
