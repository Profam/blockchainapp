package by.rabtsevich.pojo;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

@Builder
@ToString
@Getter
public class WalletUser {

    private String uderId;
    private String walletId;
}
