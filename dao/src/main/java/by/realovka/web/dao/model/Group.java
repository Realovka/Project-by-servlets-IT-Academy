package by.realovka.web.dao.model;

import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@EqualsAndHashCode(exclude = {"trainer", "students", "themes"})
@ToString(exclude = {"trainer", "students", "themes"})
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "groups_hb2")
public class Group {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    @OneToOne(mappedBy = "group")
    private Trainer trainer;
    @ManyToMany
    @JoinTable(
            name = "group_student2",
            joinColumns = @JoinColumn(name = "group_id"),
            inverseJoinColumns = @JoinColumn(name = "student_id")
    )
    private List<Student> students = new ArrayList<>();
    @OneToMany(mappedBy = "group")
    private List<Theme> themes = new ArrayList<>();
}
