package by.realovka.web.dao.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.With;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@With
@Builder
public class StudentDto {

    private Long id;
    private String name;
    private GroupDto group;
    private List<ThemeDto> theme;
}
