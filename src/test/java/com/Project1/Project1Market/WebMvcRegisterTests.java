package com.Project1.Project1Market;

import java.util.Random;
import com.Project1.Project1Market.models.User;
import net.bytebuddy.utility.RandomString;
import static org.hamcrest.Matchers.containsString;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@AutoConfigureMockMvc
public class WebMvcRegisterTests {
    @Autowired
        private MockMvc mockMvc;
    
    @Test
    public void testRegisterWithRightCredentials() throws Exception {
        Random rnd = new Random();
        String SALTCHARS = "1234567890";
        StringBuilder salt = new StringBuilder();
        while (salt.length() < 12) { // length of the random string.
            int index = (int) (rnd.nextFloat() * SALTCHARS.length());
            salt.append(SALTCHARS.charAt(index));
        }
        String saltStr = salt.toString();
        
        mockMvc.perform(get("/register"))
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("Have an account?")));

        String email = RandomString.make(10).toLowerCase() + "@mail.com";
        String password = RandomString.make(10).toLowerCase();
        String city = RandomString.make(10).toLowerCase();
        String address = RandomString.make(10).toLowerCase();
        String phone = saltStr;
        
        User user = new User();
        user.setEmail(email);
        user.setName("Hudya");
        user.setPassword(password);
        user.setCity(city);
        user.setAddress(address);
        user.setPhone(phone);

        mockMvc.perform(post("/register")
                .flashAttr("user", user))
                .andExpect(status().is3xxRedirection())
                .andExpect(MockMvcResultMatchers.redirectedUrl("/login"));
    }
    
    @Test
    public void testRegisterWithoutName() throws Exception {
        Random rnd = new Random();
        String SALTCHARS = "1234567890";
        StringBuilder salt = new StringBuilder();
        while (salt.length() < 12) { // length of the random string.
            int index = (int) (rnd.nextFloat() * SALTCHARS.length());
            salt.append(SALTCHARS.charAt(index));
        }
        String saltStr = salt.toString();
        
        mockMvc.perform(get("/register"))
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("Have an account?")));

        String email = RandomString.make(10).toLowerCase() + "@mail.com";
        String password = RandomString.make(10).toLowerCase();
        String city = RandomString.make(10).toLowerCase();
        String address = RandomString.make(10).toLowerCase();
        String phone = saltStr;
        
        User user = new User();
        user.setEmail(email);
        user.setName("");
        user.setPassword(password);
        user.setCity(city);
        user.setAddress(address);
        user.setPhone(phone);

        mockMvc.perform(post("/register")
                .flashAttr("user", user))
                .andExpect(status().is3xxRedirection())
                .andExpect(MockMvcResultMatchers.redirectedUrl("/register"))
                .andExpect(MockMvcResultMatchers
                        .flash().attributeExists("danger")
                )
                .andExpect(
                        MockMvcResultMatchers
                                .flash()
                                .attribute("danger", "Name cannot be null!")
                );
    }
    
    @Test
    public void testRegisterWithoutPassword() throws Exception {
        Random rnd = new Random();
        String SALTCHARS = "1234567890";
        StringBuilder salt = new StringBuilder();
        while (salt.length() < 12) { // length of the random string.
            int index = (int) (rnd.nextFloat() * SALTCHARS.length());
            salt.append(SALTCHARS.charAt(index));
        }
        String saltStr = salt.toString();
        
        mockMvc.perform(get("/register"))
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("Have an account?")));

        String email = RandomString.make(10).toLowerCase() + "@mail.com";
        String password = RandomString.make(10).toLowerCase();
        String city = RandomString.make(10).toLowerCase();
        String address = RandomString.make(10).toLowerCase();
        String phone = saltStr;
        
        User user = new User();
        user.setEmail(email);
        user.setName("Hudya");
        user.setPassword("");
        user.setCity(city);
        user.setAddress(address);
        user.setPhone(phone);

        mockMvc.perform(post("/register")
                .flashAttr("user", user))
                .andExpect(status().is3xxRedirection())
                .andExpect(MockMvcResultMatchers.redirectedUrl("/register"))
                .andExpect(MockMvcResultMatchers
                        .flash().attributeExists("danger")
                )
                .andExpect(
                        MockMvcResultMatchers
                                .flash()
                                .attribute("danger", "Password cannot be null!")
                );
    }
    
    @Test
    public void testRegisterWithoutCity() throws Exception {
        Random rnd = new Random();
        String SALTCHARS = "1234567890";
        StringBuilder salt = new StringBuilder();
        while (salt.length() < 12) { // length of the random string.
            int index = (int) (rnd.nextFloat() * SALTCHARS.length());
            salt.append(SALTCHARS.charAt(index));
        }
        String saltStr = salt.toString();
        
        mockMvc.perform(get("/register"))
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("Have an account?")));

        String email = RandomString.make(10).toLowerCase() + "@mail.com";
        String password = RandomString.make(10).toLowerCase();
        String city = RandomString.make(10).toLowerCase();
        String address = RandomString.make(10).toLowerCase();
        String phone = saltStr;
        
        User user = new User();
        user.setEmail(email);
        user.setName("Hudya");
        user.setPassword(password);
        user.setCity("");
        user.setAddress(address);
        user.setPhone(phone);

        mockMvc.perform(post("/register")
                .flashAttr("user", user))
                .andExpect(status().is3xxRedirection())
                .andExpect(MockMvcResultMatchers.redirectedUrl("/register"))
                .andExpect(MockMvcResultMatchers
                        .flash().attributeExists("danger")
                )
                .andExpect(
                        MockMvcResultMatchers
                                .flash()
                                .attribute("danger", "City cannot be null!")
                );
    }
    
    @Test
    public void testRegisterWithoutAddress() throws Exception {
        Random rnd = new Random();
        String SALTCHARS = "1234567890";
        StringBuilder salt = new StringBuilder();
        while (salt.length() < 12) { // length of the random string.
            int index = (int) (rnd.nextFloat() * SALTCHARS.length());
            salt.append(SALTCHARS.charAt(index));
        }
        String saltStr = salt.toString();
        
        mockMvc.perform(get("/register"))
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("Have an account?")));

        String email = RandomString.make(10).toLowerCase() + "@mail.com";
        String password = RandomString.make(10).toLowerCase();
        String city = RandomString.make(10).toLowerCase();
        String address = RandomString.make(10).toLowerCase();
        String phone = saltStr;
        
        User user = new User();
        user.setEmail(email);
        user.setName("Hudya");
        user.setPassword(password);
        user.setCity(city);
        user.setAddress("");
        user.setPhone(phone);

        mockMvc.perform(post("/register")
                .flashAttr("user", user))
                .andExpect(status().is3xxRedirection())
                .andExpect(MockMvcResultMatchers.redirectedUrl("/register"))
                .andExpect(MockMvcResultMatchers
                        .flash().attributeExists("danger")
                )
                .andExpect(
                        MockMvcResultMatchers
                                .flash()
                                .attribute("danger", "Address cannot be null!")
                );
    }
    
    @Test
    public void testRegisterWithoutPhone() throws Exception {
        Random rnd = new Random();
        String SALTCHARS = "1234567890";
        StringBuilder salt = new StringBuilder();
        while (salt.length() < 12) { // length of the random string.
            int index = (int) (rnd.nextFloat() * SALTCHARS.length());
            salt.append(SALTCHARS.charAt(index));
        }
        String saltStr = salt.toString();
        
        mockMvc.perform(get("/register"))
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("Have an account?")));

        String email = RandomString.make(10).toLowerCase() + "@mail.com";
        String password = RandomString.make(10).toLowerCase();
        String city = RandomString.make(10).toLowerCase();
        String address = RandomString.make(10).toLowerCase();
        String phone = saltStr;
        
        User user = new User();
        user.setEmail(email);
        user.setName("Hudya");
        user.setPassword(password);
        user.setCity(city);
        user.setAddress(address);
        user.setPhone("");

        mockMvc.perform(post("/register")
                .flashAttr("user", user))
                .andExpect(status().is3xxRedirection())
                .andExpect(MockMvcResultMatchers.redirectedUrl("/register"))
                .andExpect(MockMvcResultMatchers
                        .flash().attributeExists("danger")
                )
                .andExpect(
                        MockMvcResultMatchers
                                .flash()
                                .attribute("danger", "Phone cannot be null!")
                );
    }
    
    @Test
    public void testRegisterThenLogin() throws Exception {
        Random rnd = new Random();
        String SALTCHARS = "1234567890";
        StringBuilder salt = new StringBuilder();
        while (salt.length() < 12) { // length of the random string.
            int index = (int) (rnd.nextFloat() * SALTCHARS.length());
            salt.append(SALTCHARS.charAt(index));
        }
        String saltStr = salt.toString();
        
        mockMvc.perform(get("/register"))
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("Have an account?")));

        String email = RandomString.make(10).toLowerCase() + "@mail.com";
        String password = RandomString.make(10).toLowerCase();
        String city = RandomString.make(10).toLowerCase();
        String address = RandomString.make(10).toLowerCase();
        String phone = saltStr;
        
        User user = new User();
        user.setEmail(email);
        user.setName("Hudya");
        user.setPassword(password);
        user.setCity(city);
        user.setAddress(address);
        user.setPhone(phone);

        mockMvc.perform(post("/register")
                .flashAttr("user", user))
                .andExpect(status().is3xxRedirection())
                .andExpect(MockMvcResultMatchers.redirectedUrl("/login"));

        mockMvc.perform(get("/login"))
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("Login")));

        User userLogin = new User();
        userLogin.setEmail(email);
        userLogin.setPassword(password);
        
        mockMvc.perform(post("/login")
                .flashAttr("user", userLogin))
                .andExpect(status().is3xxRedirection())
                .andExpect(MockMvcResultMatchers.redirectedUrl("/"))
                .andDo(print());
    }
}