package by.realovka.web.dao.model;

import lombok.*;

import javax.persistence.*;


@Data
@AllArgsConstructor
@NoArgsConstructor
@With
@Builder
@Entity
@Table(name = "themes_hb2")
public class Theme {
    @Id
    @GeneratedValue(strategy = GenerationType.TABLE)
    private Long id;
    @Column(name = "name_theme")
    private String name;
    private int mark;
    @ManyToOne
    @JoinColumn(name = "group_id")
    private Group group;
    @ManyToOne
    @JoinColumn(name = "student_id")
    private Student student;

}
