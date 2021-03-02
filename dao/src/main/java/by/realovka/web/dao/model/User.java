package by.realovka.web.dao.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.With;
import lombok.experimental.SuperBuilder;

import javax.persistence.*;


@Data
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
@With
@Entity
@Table(name = "users_hb2")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "status")
public  class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "user_name")
    private String userName;
    private int age;
    private String login;
    @Column(name = "login_and_password")
    private String loginAndPassword;

}
