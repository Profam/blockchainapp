package by.rabtsevich.service;

import by.rabtsevich.pojo.Wallet;
import by.rabtsevich.repository.WalletRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class WalletService {

    @Autowired
    @Value("#{walletRepository}")
    WalletRepository walletRepository;

    public Wallet get(String walletId) {
        return walletRepository.findByWalletId(walletId);
    }

    public void update(Wallet wallet) {
        walletRepository.save(wallet);
    }

}
