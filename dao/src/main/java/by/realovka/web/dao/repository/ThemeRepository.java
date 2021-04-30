package by.realovka.web.dao.repository;

import by.realovka.web.dao.model.Theme;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ThemeRepository extends JpaRepository<Theme, Long> {

    Theme findThemeById(Long id);
    List<Theme> findThemeByGroup_IdAndStudentId(Long groupId, Long studentId);

}
