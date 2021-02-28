package by.realovka.web.dao.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Embeddable
public class GroupStudent {
    @Column(name = "group_id")
    private Long groupId;
    @Column(name = "student_id")
    private Long studentId;
}
