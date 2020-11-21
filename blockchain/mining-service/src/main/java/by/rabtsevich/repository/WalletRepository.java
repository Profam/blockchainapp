package by.rabtsevich.repository;

import by.rabtsevich.pojo.Wallet;
import org.springframework.data.repository.CrudRepository;

public interface WalletRepository extends CrudRepository<Wallet, String> {
  Wallet findByWalletId(String walletId);
}
