package org.BinarAcademy.Challenge_4.services;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.json.JsonMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.github.javafaker.Faker;
import org.BinarAcademy.Challenge_4.model.users.User;
import org.BinarAcademy.Challenge_4.repository.user.UserRepository;
import org.BinarAcademy.Challenge_4.service.UserService.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class UserServiceTest {
    @Mock
    private UserRepository repository;
    private UserService userService;

    Faker faker;
//    ObjectMapper Obj;
    String result;

    @BeforeEach
    void setUp()
    {
//        Obj = JsonMapper.builder().findAndAddModules().build();
//        Obj.registerModule(new JavaTimeModule());
        this.userService = new UserService(this.repository);
        faker = new Faker(new Locale("en-US"));
    }

    @Test
    @DisplayName("Adding New User Test")
    void addNewUserTest(){
        assertNotNull(userService);
        User newUser = new User();
        newUser.setId(1);
        newUser.setNama(faker.name().name());
        newUser.setPassword(faker.internet().password());
        newUser.setEmail(faker.internet().emailAddress());

        when(repository.save(any(User.class)))
                .thenReturn(newUser);
        User savedUser = repository.save(newUser);

        assertThat(savedUser.getNama()).isNotNull();
//        result = Obj.writeValueAsString(savedUser);
//        System.out.println("When creating User : "+result);
    }

    @Test
    @DisplayName("Get User Test")
    void getUserTest(){
        String newUserString;
        assertNotNull(userService);
        Faker faker = new Faker(new Locale("en-US"));
        User newUser = new User();
        newUser.setId(1);
        newUser.setNama(faker.name().name());
        newUser.setPassword(faker.internet().password());
        newUser.setEmail(faker.internet().emailAddress());
//        newUserString = Obj.writeValueAsString(newUser);
        List<User> userList = new ArrayList<>();
        userList.add(newUser);

        when(repository.findAll())
                .thenReturn(userList);

        List<User> fetchedUser = userService.getAllUser();
        assertThat(fetchedUser.size()).isGreaterThan(0);

//        for(User u : fetchedUser) {
//            result = Obj.writeValueAsString(u);
//            System.out.println("When creating user : " + newUserString);
//            System.out.println("When retrieving user : " + result);
//        }
    }

    @Test
    @DisplayName("Delete User Test")
    void deleteUserTest()
    {
        assertNotNull(userService);
        Faker faker = new Faker(new Locale("en-US"));
        User newUser = new User();
        newUser.setId(1);
        newUser.setNama(faker.name().name());
        newUser.setPassword(faker.internet().password());
        newUser.setEmail(faker.internet().emailAddress());
        List<User> userList = new ArrayList<>();
        userList.add(newUser);

        BDDMockito.given(repository.findUserByNama(newUser.getNama()))
                .willReturn(newUser);

        BDDMockito.willDoNothing().given(repository).delete(newUser);

        userService.deleteUser(newUser.getNama());

        Mockito.verify(repository, Mockito.times(1)).findUserByNama(newUser.getNama());
        Mockito.verify(repository, Mockito.times(1)).delete(newUser);

    }
}