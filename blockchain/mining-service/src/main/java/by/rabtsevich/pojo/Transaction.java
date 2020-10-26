package by.rabtsevich.pojo;

import lombok.Builder;
import lombok.ToString;

//transaction - операция по переводу одного или нескольких блоков с одного кошелька на другой
@Builder
@ToString
public class Transaction {
    private String transactionId; //Contains a hash of transaction
    private String senderWalletId; //Senders address/public key.
    private String senderSecretkey; //Senders secret key.
    private String receiverWaletId; //Recipients address/public key.
    private int value; //Contains the amount we wish to send to the recipient.


    //private List<String> blockHash;
    //private byte[] signature; //This is to prevent anybody else from spending funds in our wallet.
}
