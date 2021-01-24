package by.realovka.web.dao.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.With;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
@With
public class TrainerDTO {
    private long id;
    private String name;
    private BigDecimal averageSalary;
}
