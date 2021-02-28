package by.realovka.web.dao.model;

import lombok.*;
import lombok.experimental.SuperBuilder;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

@EqualsAndHashCode(callSuper = true, exclude = "group")
@ToString(callSuper = true, exclude = "group")
@Data
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
@With
@Entity
@DiscriminatorValue("TRAINER")
public class Trainer extends User {

    @OneToOne
    @JoinColumn(name = "group_id")
    private Group group;

}
