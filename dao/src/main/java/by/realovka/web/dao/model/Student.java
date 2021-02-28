package by.realovka.web.dao.model;

import lombok.*;
import lombok.experimental.SuperBuilder;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import java.util.ArrayList;
import java.util.List;

@EqualsAndHashCode(callSuper = true,  exclude = {"groups", "themes"})
@ToString(callSuper = true, exclude = {"groups", "themes"})
@Data
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
@With
@Entity
@DiscriminatorValue("STUDENT")
public class Student extends User {
    @ManyToMany(mappedBy = "students")
    private List<Group> groups = new ArrayList<>();
    @OneToMany(mappedBy = "student")
    private List<Theme> themes = new ArrayList<>();

}
