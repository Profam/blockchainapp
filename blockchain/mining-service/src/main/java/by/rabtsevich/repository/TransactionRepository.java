package by.rabtsevich.repository;

import by.rabtsevich.pojo.Transaction;
import org.springframework.data.repository.CrudRepository;

public interface TransactionRepository extends CrudRepository<Transaction, String> {
    Transaction findFirstByStatus(String status);
}
