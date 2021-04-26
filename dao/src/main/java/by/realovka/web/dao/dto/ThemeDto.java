package by.realovka.web.dao.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.With;

@Data
@AllArgsConstructor
@NoArgsConstructor
@With
@Builder
public class ThemeDto {

    private Long id;
    private GroupDto group;
    private String name;
    private int mark;
}
