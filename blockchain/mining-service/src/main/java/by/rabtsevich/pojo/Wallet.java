package by.rabtsevich.pojo;

import lombok.Data;

@Data
public class Wallet {
    //wallet id - уникальный идентификатор ключа
    //secretKey - секретный ключ для подписания и проверки транзакций
    private String walletId;
    private String secretKey;
}
