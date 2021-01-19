package by.realovka.web.dao.model;

import lombok.*;
import lombok.experimental.SuperBuilder;

import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@With
public class User {

    private Long id;
    private String userName;
    private int age;
    private String login;
    private StringBuilder loginAndPassword;
    private Role role;
    private long groupId;
    private List<User> students = new ArrayList<>();
    private List<Theme> themes = new ArrayList<>();

    public User(String login) {
        this.login = login;
    }

    public User(Long id, String userName, String login, Role role, List<Theme> themes) {
        this.id = id;
        this.userName = userName;
        this.login = login;
        this.role = role;
        this.themes = themes;
    }

    public User(Long id, String userName, String login, Role role, long groupId) {
        this.id = id;
        this.userName = userName;
        this.login = login;
        this.role = role;
        this.groupId = groupId;
    }

    public User(Long id, String userName, String login) {
        this.id = id;
        this.userName = userName;
        this.login = login;
    }

    public User(String userName, int age, String login, StringBuilder loginAndPassword, Role role) {
        this.userName = userName;
        this.age = age;
        this.login = login;
        this.loginAndPassword = loginAndPassword;
        this.role = role;
    }

    public User(String userName, Role role) {
        this.userName = userName;
        this.role = role;
    }

    public User(Long id, String userName, String login, Role role) {
        this.id = id;
        this.userName = userName;
        this.login = login;
        this.role = role;
    }


}
