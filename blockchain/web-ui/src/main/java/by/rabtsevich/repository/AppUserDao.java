package by.rabtsevich.repository;

public interface AppUserDao<T> {

    void create(T t);

    T find(String id);

}
