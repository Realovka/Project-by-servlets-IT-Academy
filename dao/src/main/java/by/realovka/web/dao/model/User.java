package by.realovka.web.dao.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public abstract class User {

    private static Long increment = 1L;
    private Long id = increment++;
    private String userName;
    private int age;
    private String login;
    private String password;


    public User(String userName, int age) {
        this.userName = userName;
        this.age = age;
    }

    public User(String login, String password) {
        this.login = login;
        this.password = password;
    }
}
