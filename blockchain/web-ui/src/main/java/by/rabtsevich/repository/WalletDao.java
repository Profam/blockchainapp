package by.rabtsevich.repository;

import java.util.List;

public interface WalletDao<T> {

    void create(T t);

    T find(String id);

    List<T> findAll(String searchStr);
}
