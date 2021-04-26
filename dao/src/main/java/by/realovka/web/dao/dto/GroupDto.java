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
public class GroupDto {

    private Long id;
    private TrainerDto trainer;
    private List<StudentDto> students;
    private List<ThemeDto> themes;
}
