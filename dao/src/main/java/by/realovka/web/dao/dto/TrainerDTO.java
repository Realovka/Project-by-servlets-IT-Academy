package by.realovka.web.dao.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class TrainerDTO {

    private String trainerName;
    private Double averageSalary;
    private int months;

}
