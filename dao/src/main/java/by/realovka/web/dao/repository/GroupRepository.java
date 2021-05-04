package by.realovka.web.dao.repository;

import by.realovka.web.dao.model.Group;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GroupRepository extends JpaRepository<Group, Long> {

    Group findGroupById(Long id);
}
