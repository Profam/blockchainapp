package by.rabtsevich.repository;

import by.rabtsevich.pojo.Transaction;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository("transactionRepository")
public class TransactionRepository implements TransactionDao<Transaction> {

    @Autowired
    SessionFactory sessionFactory;

    @Override
    @Transactional
    public void create(Transaction transaction) {
        sessionFactory
                .getCurrentSession()
                .saveOrUpdate(transaction);
    }

    @Override
    @Transactional
    public void update(Transaction transaction) {
        sessionFactory.getCurrentSession()
                .update(transaction);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, readOnly = true)
    public Transaction find(String id) {
        return sessionFactory
                .getCurrentSession()
                .createQuery("from Transaction t where t.id=:id", Transaction.class)
                .setParameter("id", id)
                .list()
                .stream()
                .findFirst()
                .orElse(null);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Transaction> findAllTransactionsBySenderWalletId(String walletId) {
        return sessionFactory
                .getCurrentSession()
                .createQuery("from Transaction t where t.walletId like :walletId", Transaction.class)
                .setParameter("walletId", walletId)
                .list();
    }

    @Override
    @Transactional(readOnly = true)
    public List<Transaction> findAllTransactionsByReceiverId(String receiverWalletId) {
        return sessionFactory
                .getCurrentSession()
                .createQuery("from Transaction t where t.receiverWalletId like :receiver_wallet_id", Transaction.class)
                .setParameter("receiver_wallet_id", receiverWalletId)
                .list();
    }
}
