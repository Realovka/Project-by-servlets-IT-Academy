package by.realovka.web.dao.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.SuperBuilder;

import javax.persistence.CascadeType;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;
import java.util.ArrayList;
import java.util.List;

@EqualsAndHashCode(callSuper = true,  exclude = {"groups", "themes"})
@ToString(callSuper = true, exclude = {"groups", "themes"})
@Data
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
@Entity
@DiscriminatorValue("STUDENT")
public class Student extends User {
    @ManyToMany(mappedBy = "students")
    private List<Group> groups = new ArrayList<>();
    @OneToMany(mappedBy = "student", cascade = CascadeType.ALL)
    @OrderBy
    private List<Theme> themes = new ArrayList<>();

}
