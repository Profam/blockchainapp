package by.rabtsevich.pojo;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

@Builder
@ToString
@Getter
public class User {

    private String userId;
    private String userName;
    private String userPassword;
    private String email;
    private String mobileNumber;
}
