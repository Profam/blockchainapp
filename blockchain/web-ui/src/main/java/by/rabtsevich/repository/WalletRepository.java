package by.rabtsevich.repository;

import by.rabtsevich.pojo.Wallet;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository("walletRepository")
public class WalletRepository implements WalletDao<Wallet> {

    @Autowired
    SessionFactory sessionFactory;

    @Override
    @Transactional
    public void create(Wallet wallet) {
        sessionFactory
                .getCurrentSession()
                .saveOrUpdate(wallet);
    }


    @Transactional(propagation = Propagation.REQUIRED, readOnly = true)
    public Wallet find(String walletId) {
        return sessionFactory
                .getCurrentSession()
                .createQuery("from Wallet w where w.walletId=:walletId", Wallet.class)
                .setParameter("walletId", walletId)
                .list()
                .stream()
                .findFirst()
                .orElse(null);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Wallet> findAll(String searchStr) {
        return sessionFactory
                .getCurrentSession()
                .createQuery("from Wallet w where w.walletOwner=:walletOwner", Wallet.class)
                .setParameter("walletOwner", searchStr)
                .list();
    }
}
