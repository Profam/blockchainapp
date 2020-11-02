package by.rabtsevich.service;

import by.rabtsevich.pojo.Transaction;
import by.rabtsevich.repository.TransactionDao;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TransactionService {

    @Autowired
    @Value("#{transactionRepository}")
    TransactionDao<Transaction> transactionRepository;

    @Autowired
    WalletService walletService;

    private static final Logger log = LoggerFactory.getLogger(TransactionService.class);

    public boolean createNewTransaction(String senderId, Transaction transaction) {
        log.info("creating transaction in TransactionService {}", transaction);
        transaction.setWalletId(senderId);
        if (transactionRepository.find(transaction.getId()) != null) {
            return false;
        }
        transactionRepository.create(transaction);
        return true;
    }

    public boolean validateTransaction(Transaction transaction, String walletId, String secretKey) {
        return (((walletService.get(transaction.getReceiverId())) != null)
                && (transaction.getValue() > 0)
                && ((walletService.getBalance(walletId)) > transaction.getValue())
                && (transaction.getValue() <= 100)
                && (walletService.get(walletId).getSecretKey().equals(secretKey)))
                && (createNewTransaction(walletId, transaction));
    }


    public List<Transaction> getAllTransactionsBySenderWalletId(String walletId) {
        return transactionRepository.findAllTransactionsBySenderWalletId(walletId);
    }

    public List<Transaction> getAllTransactionsByReceiverId(String receiverId) {
        return transactionRepository.findAllTransactionsByReceiverId(receiverId);
    }

    public List<Transaction> getAllTransactions(String walletId) {
        List<Transaction> allTransactions = getAllTransactionsByReceiverId(walletId);
        log.info("size of list of received transactions {}", allTransactions.size());
        allTransactions.addAll(getAllTransactionsBySenderWalletId(walletId));
        log.info("size of lsit of received transactions plus sended transactions {}", allTransactions.size());

        return allTransactions;
    }
}
