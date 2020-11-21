package by.rabtsevich.repository;

import by.rabtsevich.pojo.Transaction;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface TransactionRepository extends CrudRepository<Transaction, String> {
    Transaction findFirstByTransactionStatus(String status);

    List<Transaction> findAllByTransactionStatus(String status);
}
