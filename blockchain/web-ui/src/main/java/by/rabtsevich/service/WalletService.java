package by.rabtsevich.service;

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

    public List<Wallet> getAll(String walletOwner) {
        return walletRepository.findAll(walletOwner);
    }

    public Wallet get(String walletId) {
        return walletRepository.find(walletId);
    }

    public void update(Wallet wallet) {
        walletRepository.update(wallet);
    }

}
