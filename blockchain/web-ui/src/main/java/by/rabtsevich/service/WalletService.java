package by.rabtsevich.service;

import by.rabtsevich.pojo.Transaction;
import by.rabtsevich.pojo.Wallet;
import by.rabtsevich.repository.WalletDao;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class WalletService {
    private static final Logger log = LoggerFactory.getLogger(WalletService.class);

    @Autowired
    @Value("#{walletRepository}")
    WalletDao<Wallet> walletRepository;

    @Autowired
    AppUserService appUserService;

    @Autowired
    TransactionService transactionService;

    public boolean createNewWallet(Wallet wallet) {
        log.info("creating new wallet: {}", wallet);
        if (walletRepository.find(wallet.getWalletId()) != null) {
            return false;
        }
        wallet.setWalletOwner(
                appUserService.findByUserName(
                        AppUserService.getAuthenticationUserUserName()).getId());
        walletRepository.create(wallet);
        return true;
    }

    public long getBalance(String walletId) {
        long outcomeBalance = 0;
        long incomeBalance = 0;

        //sended from wallet
        for (Transaction t : transactionService.getAllTransactionsBySenderWalletId(walletId)) {
            outcomeBalance += t.getValue();
        }
        //received by wallet
        for (Transaction t : transactionService.getAllTransactionsByReceiverId(walletId)) {
            incomeBalance += t.getValue();
        }
        return incomeBalance - outcomeBalance;
    }

    public List<Wallet> getAll(String walletOwner) {
        return walletRepository.findAll(walletOwner);
    }

    public Wallet get(String walletId) {
        return walletRepository.find(walletId);
    }
}
