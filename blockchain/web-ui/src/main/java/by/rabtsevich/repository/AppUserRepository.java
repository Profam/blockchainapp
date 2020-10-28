package by.rabtsevich.repository;

import by.rabtsevich.pojo.AppUser;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.io.Serializable;
import java.util.List;

@Repository("appUserRepository")
public class AppUserRepository implements GenericDao<AppUser> {

    @Autowired
    SessionFactory sessionFactory;

    @Override
    public void create(AppUser appUser) {
        sessionFactory
                .getCurrentSession()
                .saveOrUpdate(appUser);
    }

    @Override
    public void update(AppUser appUser) {
        create(appUser);
    }

    @Override
    public AppUser read(Class<AppUser> clazz, Serializable id) {
        return sessionFactory
                .getCurrentSession()
                .get(AppUser.class, id);
    }

    @Override
    @Transactional
    public void delete(AppUser appUser) {
        sessionFactory.getCurrentSession().delete(appUser);
    }

    @Override
    public AppUser find(String userId) {
        return sessionFactory
                .getCurrentSession()
                .createQuery("from AppUser u where u.userName=:username", AppUser.class)
                .setParameter("username", userId)
                .list()
                .stream()
                .findFirst()
                .orElse(null);
    }

    @Override
    public List<AppUser> findAll(String searchStr) {
        return sessionFactory
                .getCurrentSession()
                .createQuery("from AppUser", AppUser.class)
                .list();
    }
}
