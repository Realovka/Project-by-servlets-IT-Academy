package by.realovka.web.dao.dto;

import by.realovka.web.dao.model.Role;
import by.realovka.web.dao.model.Theme;
import by.realovka.web.dao.model.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.With;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
@With
@Builder
public class UserDTO {

    private String userName;
    private int age;
    private String login;
    private String password;
    private Role role;
    private List<User> students = new ArrayList<>();
    private Set<Theme> themes = new HashSet<>();
}
