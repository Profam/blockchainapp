package by.rabtsevich.service;

import by.rabtsevich.pojo.Transaction;
import by.rabtsevich.util.ValidUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.security.NoSuchAlgorithmException;

@Service
public class MiningService {
    @Autowired
    BlockService blockService;
    @Autowired
    TransactionService transactionService;
    @Autowired
    ValidUtil validUtil;

    private static final Logger log = LoggerFactory.getLogger(MiningService.class);

    private final int difficulty = 5;

    public boolean mine(String walletId) throws InterruptedException, NoSuchAlgorithmException {
        log.info("start mining, walletId {}", walletId);
        boolean flag = true;//flag - is mining running atm?
        while (flag) {
            Thread.sleep(5000);//to slow down
            Transaction pendingTransaction = transactionService.getPendingTransaction();
            //getting not accepted(new transaction)
            log.info("pending Tx {}", pendingTransaction);
            if (pendingTransaction != null) {
                //creating block without nonce and hash, then we'll mine it and saving it
                blockService.saveNewBlock(
                        blockService.mineBlock(
                                blockService.createBlock(pendingTransaction),
                                difficulty));

                flag = validUtil.isBlockchainValid();// if blockchain is corrupted or broken, flag set to false and mining will stop
                log.info("blockchain is valid {}", flag);
            }
        }
        return flag;
    }
}
