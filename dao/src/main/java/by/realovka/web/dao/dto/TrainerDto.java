package by.realovka.web.dao.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TrainerDto {

    private long id;
    private String name;
    private String login;
    private int age;
    private String loginAndPassword;
    private BigDecimal averageSalary;
    private GroupDto group;
}
