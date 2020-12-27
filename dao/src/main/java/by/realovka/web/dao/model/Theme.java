package by.realovka.web.dao.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Theme {

    private String name;
    private int mark;

    public Theme(String name) {
        this.name = name;
    }
}
