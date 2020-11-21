package by.rabtsevich.service;

import by.rabtsevich.pojo.Transaction;
import by.rabtsevich.repository.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
//service to add list of accepted transactions in block
@Service
public class TransactionService {

    @Autowired
    TransactionRepository transactionRepository;

    public List<Transaction> getAcceptedTransactions() {
        return transactionRepository.findAllByTransactionStatus("Accepted");
    }
}
