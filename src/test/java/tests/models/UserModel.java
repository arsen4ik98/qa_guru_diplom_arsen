package tests.models;

import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class UserModel {
    String id;
    String username;
    String firstName;
    String lastName;
    String email;
    String password;
    String phone;
    String userStatus;
}
