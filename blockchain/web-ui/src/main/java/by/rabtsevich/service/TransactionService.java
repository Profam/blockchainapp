package by.rabtsevich.service;

import by.rabtsevich.pojo.Transaction;
import by.rabtsevich.pojo.Wallet;
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

    public void createNewTransaction(String walletId, Transaction transaction) {
        log.info("creating transaction in TransactionService {}", transaction);
        transaction.setWalletId(walletId);
        transaction.setTransactionStatus("Pending");
        transactionRepository.create(transaction);
    }

    //check wallet balance, valid secretkey, existence of receiver wallet id
    public boolean validateTransaction(Transaction transaction, String walletId, String secretKey, String receiverWalletId) {
        return ((walletService.get(transaction.getWalletId()).getBalance() >= transaction.getValue())
                && (walletService.get(walletId).getSecretKey().equals(secretKey)))
                || (walletService.get(receiverWalletId).getWalletId().equals(receiverWalletId));
    }

    //receiver has secret key?
    public boolean verifySecretkey(Transaction transaction, String secretKey) {
        return transaction.getSenderSecretKey().equals(secretKey);
    }


    public void transferBalanceFromWalletToWallet(Transaction transaction) {
        log.info("transfer balance from wallet to wallet {}", transaction);
        Wallet walletSender = walletService.get(transaction.getWalletId());
        walletSender.setBalance(walletSender.getBalance() - transaction.getValue());
        walletService.update(walletSender);

        Wallet walletReceiver = walletService.get(transaction.getReceiverWalletId());
        walletReceiver.setBalance(walletReceiver.getBalance() + transaction.getValue());
        walletService.update(walletReceiver);

        transaction.setTransactionStatus("Accepted");
        transactionRepository.update(transaction);
    }

    public Transaction getTransaction(String id) {
        return transactionRepository.find(id);
    }

    public List<Transaction> getAllTransactionsBySenderWalletId(String walletId) {
        return transactionRepository.findAllTransactionsBySenderWalletId(walletId);
    }

    public List<Transaction> getAllTransactionsByReceiverId(String receiverWalletId) {
        return transactionRepository.findAllTransactionsByReceiverId(receiverWalletId);
    }

    public List<Transaction> getAllTransactions(String walletId) {
        List<Transaction> allTransactions = getAllTransactionsByReceiverId(walletId);
        log.info("size of list of received transactions {}", allTransactions.size());
        allTransactions.addAll(getAllTransactionsBySenderWalletId(walletId));
        log.info("size of lsit of received transactions plus sended transactions {}", allTransactions.size());
        return allTransactions;
    }
}
