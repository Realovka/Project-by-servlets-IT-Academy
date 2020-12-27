package by.realovka.web.service;

import by.realovka.web.dao.model.Admin;
import by.realovka.web.dao.model.Student;
import by.realovka.web.dao.model.Trainer;
import by.realovka.web.dao.model.User;
import by.realovka.web.dao.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

public class UserServiceImplTest {

    @InjectMocks
    private UserServiceImpl userService;

    @Mock
    private UserRepository userRepository;

    @BeforeEach
    public void init() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void UserServiceImpl_SaveUser_NotSaveDuplicatedUser() {
        Map<String, User> map = new HashMap<>();
        map.put("Student", new Student());
        userService.saveUser(new Student("Student", "Password"));
        assertEquals(1, map.size());
    }

    @Test
    void UserServiceImpl_GetAUserByLoginAndPassword_ComparisonTwoUserFromMapAndAuth() {
        Map<String, User> map = new HashMap<>();
        map.put("loginTrainer", new Trainer("loginTrainer", "passwordTrainer"));
        when(userRepository.findAll()).thenReturn(map);
        Trainer trainer = new Trainer("loginTrainer", "passwordTrainer");
        assertEquals(trainer, userService.getUserByLoginAndPassword("loginTrainer", "passwordTrainer"));
    }

    @Test
    void UserServiceImpl_GetAllStudents_GetNumberStudentsInMap(){
        Map<String, User> map = new HashMap<>();
        map.put("Student", new Student());
        map.put("StudentSecond", new Student());
        map.put("Admin", new Admin());
        map.put("Trainer", new Trainer());
        when(userRepository.findAll()).thenReturn(map);
        assertEquals(2, userService.getAllStudents().size());
    }
}
