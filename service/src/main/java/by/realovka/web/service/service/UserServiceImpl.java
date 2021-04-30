
package by.realovka.web.service.service;


import by.realovka.web.dao.dto.*;
import by.realovka.web.dao.model.*;
import by.realovka.web.dao.repository.*;
import lombok.AllArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static by.realovka.web.dao.model.Role.*;

@Slf4j
@Service
@AllArgsConstructor
@Transactional
@PropertySource("classpath:application.properties")
public class UserServiceImpl implements UserService {

    private final TrainerRepository trainerRepository;
    private final StudentRepository studentRepository;
    private final UserRepository userRepository;
    private final ThemeRepository themeRepository;
    private final GroupRepository groupRepository;

    @SneakyThrows
    @Override
    public boolean saveUser(UserDto userDto) {
        Optional<User> user = userRepository.findUsersByLogin(userDto.getLogin());
        if (user.isEmpty()) {
            String loginAndPassword = userDto.getLogin().concat(userDto.getPassword());
            MessageDigest md5 = null;
            try {
                md5 = MessageDigest.getInstance("MD5");
            } catch (NoSuchAlgorithmException e) {
                e.printStackTrace();
            }
            byte[] loginAndPasswordHash = md5.digest(loginAndPassword.getBytes());
            StringBuilder builder = new StringBuilder();
            for (byte b : loginAndPasswordHash) {
                builder.append(String.format("%02X", b));
            }
            if (userDto.getRole().equals(TRAINER)) {
                Trainer trainer = Trainer.builder()
                        .name(userDto.getLogin())
                        .age(userDto.getAge())
                        .login(userDto.getLogin())
                        .loginandpassword(builder.toString())
                        .build();
                log.info("Save new user {}", trainer);
                userRepository.save(trainer);
            } else if (userDto.getRole().equals(STUDENT)) {
                Student student = Student.builder()
                        .name(userDto.getLogin())
                        .age(userDto.getAge())
                        .login(userDto.getLogin())
                        .loginandpassword(builder.toString())
                        .build();
                log.info("Save new user {}", student);
                userRepository.save(student);
            } else if (userDto.getRole().equals(ADMIN)) {
                Admin admin = Admin.builder()
                        .name(userDto.getLogin())
                        .age(userDto.getAge())
                        .login(userDto.getLogin())
                        .loginandpassword(builder.toString())
                        .build();
                log.info("Save new user {}", admin);
                userRepository.save(admin);
            }
            return true;
        } else {
            return false;
        }
    }


    @SneakyThrows
    @Override
    public User identificationUserByLoginAndPassword(String login, String password) {
        String loginAndPasswordFromUI = login.concat(password);
        MessageDigest messageDigest = MessageDigest.getInstance("MD5");
        byte[] loginAndPasswordHashFromUI = messageDigest.digest(loginAndPasswordFromUI.getBytes());
        StringBuilder builder = new StringBuilder();
        for (byte b : loginAndPasswordHashFromUI) {
            builder.append(String.format("%02X", b));
        }
        String loginAndPassword = String.valueOf(builder);
        Optional<User> user = userRepository.findUserByLoginandpassword(loginAndPassword);
        log.info("Identification user {}", user);
        return user.orElse(new User());
    }

    @Override
    public TrainerDto getById(Long id) {
        Trainer trainer = getStudentsWithTrainerThemes((Trainer) userRepository.findUsersById(id));
        return TrainerDto.builder()
                .id(trainer.getId())
                .name(trainer.getName())
                .group(GroupDto.builder()
                        .id(trainer.getGroup().getId())
                        .students(trainer.getGroup().getStudents().stream().map(student ->
                                StudentDto.builder()
                                        .id(student.getId())
                                        .name(student.getName())
                                        .theme(student.getThemes().stream().map(theme ->
                                                ThemeDto.builder()
                                                        .id(theme.getId())
                                                        .name(theme.getName())
                                                        .mark(theme.getMark())
                                                        .build()).collect(Collectors.toList()))
                                        .build()).collect(Collectors.toList()))
                        .build())
                .build();
    }

    @Override
    public TrainerDto getTrainer(Long id) {
        Trainer trainer = trainerRepository.findTrainerById(id);
        if (trainer.getGroup() == null) {
            return TrainerDto.builder()
                    .id(trainer.getId())
                    .name(trainer.getName())
                    .age(trainer.getAge())
                    .login(trainer.getLogin())
                    .loginAndPassword(trainer.getLoginandpassword())
                    .build();
        } else {
            return TrainerDto.builder()
                    .id(trainer.getId())
                    .name(trainer.getName())
                    .age(trainer.getAge())
                    .login(trainer.getLogin())
                    .loginAndPassword(trainer.getLoginandpassword())
                    .group(GroupDto.builder()
                            .id(trainer.getGroup().getId())
                            .build())
                    .build();
        }
    }

    @Override
    public TrainerDto createGroupByTrainer(TrainerDto trainerAuth) {
        Trainer trainer = Trainer.builder()
                .id(trainerAuth.getId())
                .name(trainerAuth.getName())
                .login(trainerAuth.getLogin())
                .age(trainerAuth.getAge())
                .loginandpassword(trainerAuth.getLoginAndPassword())
                .build();
        Group group = Group.builder()
                .name("Group ".concat(trainer.getName()))
                .trainer(trainer)
                .themes(new ArrayList<>())
                .students(new ArrayList<>())
                .build();
        trainer.setGroup(group);
        trainer = trainerRepository.save(trainer);
        return getById(trainer.getId());
    }

    @Override
    public List<StudentDto> getStudentsFromUniversityWhoDontStudyAtAuthTrainer(TrainerDto trainerDto) {
        List<Student> allStudentsFromUniversity = studentRepository.findAll();
        Group group = groupRepository.findGroupById(trainerDto.getGroup().getId());
        allStudentsFromUniversity.removeAll(group.getStudents());
        return  allStudentsFromUniversity.stream()
                .map(student -> StudentDto.builder()
                        .id(student.getId())
                        .name(student.getName())
                        .theme(student.getThemes().stream().filter(theme -> theme.getGroup().getId().equals(trainerDto.getGroup().getId())).map
                                (theme ->
                                        ThemeDto.builder()
                                                .id(theme.getId())
                                                .name(theme.getName())
                                                .mark(theme.getMark())
                                                .build()).collect(Collectors.toList()))
                        .build()).collect(Collectors.toList());
    }

    @Override
    public TrainerDto addStudentToGroup(TrainerDto trainerDto, Long studentId) {
        Trainer trainer = trainerRepository.findTrainerById(trainerDto.getId());
        List<Theme> themesNewStudentInGroup = new ArrayList<>();
        Student student = studentRepository.findStudentById(studentId);
        if (trainer.getGroup().getStudents().size() > 0) {
            List<Theme> themes = themeRepository.findThemeByGroup_IdAndStudentId(trainerDto.getGroup().getId(), trainerDto.getGroup().getStudents().get(0).getId());
            for (Theme item : themes) {
                themesNewStudentInGroup.add(Theme.builder()
                        .name(item.getName())
                        .student(student)
                        .group(item.getGroup())
                        .build());
            }
        }
        student.getThemes().addAll(themesNewStudentInGroup);
        trainer.getGroup().getStudents().add(student);
        trainerRepository.save(trainer);
        for (Theme item : themesNewStudentInGroup) {
            themeRepository.save(item);
        }
        return getById(trainer.getId());
    }

    @Override
    public TrainerDto getTrainerAndHisStudentsAfterAddTheme(TrainerDto trainerDto, String nameTheme) {
        Trainer trainer = trainerRepository.findTrainerById(trainerDto.getId());
        Group group = trainer.getGroup();
        List<Theme> themes = new ArrayList<>();
        List<Student> students = trainer.getGroup().getStudents();
        for (Student item : students) {
            themes.add(Theme.builder()
                    .name(nameTheme)
                    .mark(0)
                    .student(item)
                    .group(group)
                    .build());
        }
        for (Theme item : themes) {
            themeRepository.save(item);
        }
        return getById(trainerDto.getId());
    }

    @Override
    public TrainerDto addOrUpdateOrDeleteMark(TrainerDto trainerDto, Long themeId, Integer mark) {
        Theme theme = themeRepository.findThemeById(themeId);
        theme.setMark(mark);
        themeRepository.save(theme);
        return getById(trainerDto.getId());
    }


    private Trainer getStudentsWithTrainerThemes(Trainer trainer) {
        if (trainer.getGroup() != null) {
            List<Student> students = trainer.getGroup().getStudents();
            for (Student item : students) {
                List<Theme> themes = item.getThemes().stream().
                        filter(theme -> theme.getGroup().equals(trainer.getGroup()))
                        .collect(Collectors.toList());
                item.setThemes(themes);
            }
            trainer.getGroup().setStudents(students);
        }
        return trainer;
    }

}