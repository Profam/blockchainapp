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
    //change value to speed up or slow down creation and validation of blocks
    private final int difficulty = 4;

    public boolean mine(String walletId) throws InterruptedException, NoSuchAlgorithmException {
        log.info("start mining, walletId {}", walletId);
        boolean isMining = true;
        while (isMining) {
            //change speed of creation and validation of blocks
            Thread.sleep(5000);
            //add valid block to blockchain
            blockService.saveNewBlock(
                    blockService.mineBlock(
                            blockService.createBlock(),
                            difficulty));
            //wallet reward for mining, default : 1 block = 1 coin
            short miningReward = 1;
            Wallet wallet = walletService.get(walletId);
            wallet.setBalance(wallet.getBalance() + miningReward);
            walletService.update(wallet);
            // if blockchain is not valid, mining will stop
            isMining = validationBlockService.isBlockchainValid();
            log.info("blockchain is valid {}", isMining);
        }
        return isMining;
    }
}
