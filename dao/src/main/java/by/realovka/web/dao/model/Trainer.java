package by.realovka.web.dao.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Trainer extends User {

    private List<Double> salary = new ArrayList<>();
    private List<Student> students = new ArrayList<>();

    public Trainer(Long id, String userName, int age, String login, String password, List<Double> salary, List<Student> students) {
        super(id, userName, age, login, password);
        this.salary = salary;
        this.students = students;
    }

    public Trainer(String userName, int age, List<Double> salary) {
        super(userName,age);
        this.salary = salary;
    }

    public Trainer(String login, String password) {
        super(login, password);
    }

}
