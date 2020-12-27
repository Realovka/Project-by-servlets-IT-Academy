package by.realovka.web.dao.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Student extends User {

    public Student(String userName, int age) {
        super(userName, age);
    }

    public Student(String login, String password) {
        super(login, password);
    }

    private List<Theme> themes = new ArrayList<>();

}
