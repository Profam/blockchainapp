package by.rabtsevich.service;

import by.rabtsevich.pojo.Transaction;
import by.rabtsevich.repository.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TransactionService {

    @Autowired
    TransactionRepository transactionRepository;

    public void saveTransaction(Transaction transaction) {
        transactionRepository.save(transaction);
    }

    public long count() {
        return transactionRepository.count();
    }

    public Transaction getAcceptedTransaction() {
        return transactionRepository.findFirstByTransactionStatus("Accepted");
    }

    public List<Transaction> getAcceptedTransactions() {
        return transactionRepository.findAllByTransactionStatus("Accepted");
    }
}
