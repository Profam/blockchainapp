package by.rabtsevich.repository;

import java.util.List;

public interface TransactionDao<T> {
    void create(T t);

    void update(T t);

    T find(String id);

    List<T> findAllTransactionsBySenderWalletId(String walletId);

    List<T> findAllTransactionsByReceiverId(String receiverWalletId);
}
