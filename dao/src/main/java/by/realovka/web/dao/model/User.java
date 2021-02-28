package by.realovka.web.dao.model;

import lombok.*;

import java.util.ArrayList;
import java.util.List;

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

}
