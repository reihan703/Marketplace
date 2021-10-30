/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.Project1.Project1Market;

import com.Project1.Project1Market.models.User;
import com.Project1.Project1Market.repositories.UserRepository;
import com.Project1.Project1Market.services.UserService;
import net.bytebuddy.utility.RandomString;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import static org.mockito.Mockito.when;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

/**
 *
 * @author Victorio Adam
 */
@SpringBootTest
public class UserIntegrationTest {
    @Autowired
    UserService userService;
    
    @MockBean
    UserRepository userRepository;
    
    //Register Test
    @Test
    public void createUserTest() throws Exception {
        
        User user = new User();
        
        user.setEmail("test@mail.com");
        user.setName("Test");
        user.setPassword("test-strong-password");
        user.setAddress("disini");
        user.setCity("depok");
        user.setPhone("085921706170");
        
        when(userRepository.save(user)).thenReturn(user);
        userService.register(user);
        
        when(userRepository.findByEmail("test@mail.com")).thenReturn(user);
        
        User checkUser = this.userRepository.findByEmail("test@mail.com");
        
        Assertions.assertEquals(user, checkUser);
    }
    
    @Test
    public void createUserTestWithEmptyName() throws Exception {
        try{
            User user = new User();
            user.setEmail("test@mail.com");
            user.setName("");
            user.setPassword("test-strong-password");
            user.setAddress("disini");
            user.setCity("depok");
            user.setPhone("085921706170");

            when(userRepository.save(user))
                    .thenThrow(new IllegalArgumentException("name cannot be null!"));

            userService.register(user);
        }catch(Exception e){
            Assertions.assertTrue(e instanceof Exception);
            Assertions.assertEquals("name cannot be null!", e.getMessage());
        }
    }
    
    @Test
    public void createUserTestWithEmptyPassword() throws Exception {
        try{
            User user = new User();
            user.setEmail("test@mail.com");
            user.setName("Test");
            user.setPassword("");
            user.setAddress("disini");
            user.setCity("depok");
            user.setPhone("085921706170");

            when(userRepository.save(user))
                    .thenThrow(new IllegalArgumentException("password cannot be null!"));

            userService.register(user);
        }catch(Exception e){
            Assertions.assertTrue(e instanceof Exception);
            Assertions.assertEquals("password cannot be null!", e.getMessage());
        }
    }
    
    @Test
    public void createUserTestWithEmptyCity() throws Exception {
        try{
            User user = new User();
            user.setEmail("test@mail.com");
            user.setName("Test");
            user.setPassword("test-strong-password");
            user.setAddress("disini");
            user.setCity("");
            user.setPhone("085921706170");

            when(userRepository.save(user))
                    .thenThrow(new IllegalArgumentException("city cannot be null!"));

            userService.register(user);
        }catch(Exception e){
            Assertions.assertTrue(e instanceof Exception);
            Assertions.assertEquals("city cannot be null!", e.getMessage());
        }
    }
    
    @Test
    public void createUserTestWithEmptyAddress() throws Exception {
        try{
            User user = new User();
            user.setEmail("test@mail.com");
            user.setName("Test");
            user.setPassword("test-strong-password");
            user.setAddress("");
            user.setCity("depok");
            user.setPhone("085921706170");

            when(userRepository.save(user))
                    .thenThrow(new IllegalArgumentException("address cannot be null!"));

           userService.register(user);
        }catch(Exception e){
            Assertions.assertTrue(e instanceof Exception);
            Assertions.assertEquals("address cannot be null!", e.getMessage());
        }
    }

    @Test
    public void createUserTestWithEmptyPhone() throws Exception {
        try{
            User user = new User();
            user.setEmail("test@mail.com");
            user.setName("Test");
            user.setPassword("test-strong-password");
            user.setAddress("disini");
            user.setCity("depok");
            user.setPhone("");

            when(userRepository.save(user))
                    .thenThrow(new IllegalArgumentException("Phone length must between 12 and 14!"));

            userService.register(user);
        }catch(Exception e){
            Assertions.assertTrue(e instanceof Exception);
            Assertions.assertEquals("Phone length must between 12 and 14!", e.getMessage());
        }
    }
    
    //Auth Test
    @Test
    public void authTest() throws Exception {

        User user = new User();

        user.setEmail("test@mail.com");
        user.setName("Test");
        user.setPassword("test-strong-password");
        user.setAddress("disini");
        user.setCity("depok");
        user.setPhone("085921706170");

        when(userRepository.save(user)).thenReturn(user);
        userService.register(user);

        when(userRepository.findByEmail("test@mail.com")).thenReturn(user);

        User checkUser = userService.auth("test@mail.com", "test-strong-password");


        Assertions.assertEquals(user, checkUser);
    }

    @Test
    public void authTestWrongEmail() throws Exception {

        User user = new User();

        user.setEmail("test@mail.com");
        user.setName("Test");
        user.setPassword("test-strong-password");
        user.setAddress("disini");
        user.setCity("depok");
        user.setPhone("085921706170");

        when(userRepository.save(user)).thenReturn(user);
        userService.register(user);

        when(userRepository.findByEmail("test@mail.com")).thenReturn(user);

        User checkUser = userService.auth("test1@mail.com", "test-strong-password");


        Assertions.assertEquals(null, checkUser);
    }

    @Test
    public void authTestWrongPassword() throws Exception {

        User user = new User();

        user.setEmail("test@mail.com");
        user.setName("Test");
        user.setPassword("test-strong-password");
        user.setAddress("disini");
        user.setCity("depok");
        user.setPhone("085921706170");

        when(userRepository.save(user)).thenReturn(user);
        userService.register(user);

        when(userRepository.findByEmail("test@mail.com")).thenReturn(user);

        User checkUser = userService.auth("test@mail.com", "test-strong-password1");


        Assertions.assertEquals(null, checkUser);
    }

    @Test
    public void authTestWrongMailAndPassword() throws Exception {

        User user = new User();

        user.setEmail("test@mail.com");
        user.setName("Test");
        user.setPassword("test-strong-password");
        user.setAddress("disini");
        user.setCity("depok");
        user.setPhone("085921706170");

        when(userRepository.save(user)).thenReturn(user);
        userService.register(user);

        when(userRepository.findByEmail("test@mail.com")).thenReturn(user);

        User checkUser = userService.auth("test1@mail.com", "test-strong-password1");


        Assertions.assertEquals(null, checkUser);
    }

    @Test
    public void createUserTestLongName() throws Exception {

        User user = new User();

        user.setEmail("test@mail.com");
        user.setName(RandomString.make(110).toLowerCase());
        user.setPassword("test-strong-password");
        user.setAddress("disini");
        user.setCity("depok");
        user.setPhone("085921706170");

        try{
            when(userRepository.save(user)).thenReturn(user);
            userService.register(user);
        }catch(Exception e){
            Assertions.assertTrue(e instanceof Exception);
            Assertions.assertEquals("Name is too long", e.getMessage());
        }
    }

    @Test
    public void createUserTestLongAddress() throws Exception {

        User user = new User();

        user.setEmail("test@mail.com");
        user.setName("Test");
        user.setPassword("test-strong-password");
        user.setAddress(RandomString.make(110).toLowerCase());
        user.setCity("depok");
        user.setPhone("085921706170");

        try{
            when(userRepository.save(user)).thenReturn(user);
            userService.register(user);
        }catch(Exception e){
            Assertions.assertTrue(e instanceof Exception);
            Assertions.assertEquals("Address is too long", e.getMessage());
        }
    }

    @Test
    public void createUserTestLongCity() throws Exception {

        User user = new User();

        user.setEmail("test@mail.com");
        user.setName("Test");
        user.setPassword("test-strong-password");
        user.setAddress("disini");
        user.setCity(RandomString.make(110).toLowerCase());
        user.setPhone("085921706170");

        try{
            when(userRepository.save(user)).thenReturn(user);
            userService.register(user);
        }catch(Exception e){
            Assertions.assertTrue(e instanceof Exception);
            Assertions.assertEquals("City is too long", e.getMessage());
        }
    }
}
