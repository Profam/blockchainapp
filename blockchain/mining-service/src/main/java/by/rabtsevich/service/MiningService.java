package by.rabtsevich.service;

import by.rabtsevich.pojo.Wallet;
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
    ValidationBlockService validationBlockService;
    @Autowired
    WalletService walletService;

    private static final Logger log = LoggerFactory.getLogger(MiningService.class);

    private final int difficulty = 4;

    public boolean mine(String walletId) throws InterruptedException, NoSuchAlgorithmException {
        log.info("start mining, walletId {}", walletId);
        boolean flag = true;//flag - is mining running atm?
        while (flag) {
            Thread.sleep(5000);//to slow down
            //creating block without nonce and hash, then we'll mine it and saving it
            blockService.saveNewBlock(
                    blockService.mineBlock(
                            blockService.createBlock(),
                            difficulty));
            Wallet wallet = walletService.get(walletId);
            wallet.setBalance(wallet.getBalance() + 1);
            walletService.update(wallet);
            flag = validationBlockService.isBlockchainValid();// if blockchain is corrupted or broken, flag set to false and mining will stop
            log.info("blockchain is valid {}", flag);
        }
        return flag;
    }
}
