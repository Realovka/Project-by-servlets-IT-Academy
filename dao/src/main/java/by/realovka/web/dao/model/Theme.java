package by.realovka.web.dao.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.With;

@Data
@AllArgsConstructor
@NoArgsConstructor
@With
public class Theme  {
    private Long id;
    private long idGroup;
    private String name;
    private int mark;

}
