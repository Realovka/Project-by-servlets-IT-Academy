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
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;


@EqualsAndHashCode(callSuper = true, exclude = "group")
@ToString(callSuper = true, exclude = "group")
@Data
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
@Entity
@DiscriminatorValue("TRAINER")
public class Trainer extends User {
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "group_id")
    private Group group;

}
