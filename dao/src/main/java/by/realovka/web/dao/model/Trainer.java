package by.realovka.web.dao.model;

import lombok.*;

import java.math.BigDecimal;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@With
public class Trainer {
    private long id;
    private String name;
    private List<BigDecimal> salary;
}
