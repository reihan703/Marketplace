package com.Project1.Project1Market;

import com.Project1.Project1Market.models.SuggestModel;
import com.Project1.Project1Market.models.User;
import java.util.HashMap;
import net.bytebuddy.utility.RandomString;

import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.junit.jupiter.api.Test;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@AutoConfigureMockMvc
public class WebMvcFeedTests {
    
    @Autowired
    private MockMvc mockMvc;

    //Create Feedback Flow
    @Test
    public void testCreateFeedBack() throws Exception {

        String email = RandomString.make(10).toLowerCase() + "@gmail.com";
        String password = RandomString.make(10).toLowerCase();

        User user = new User();
        user.setEmail(email);
        user.setName("Test");
        user.setPassword(password);
        user.setAddress("disini");
        user.setCity("depok");
        user.setPhone("085921706170");

        mockMvc.perform(post("/register")
                .flashAttr("user", user))
                .andExpect(status().is3xxRedirection())
                .andExpect(MockMvcResultMatchers.redirectedUrl("/login"));

        User userLogin = new User();
        userLogin.setEmail(email);
        userLogin.setPassword(password);

        mockMvc.perform(post("/login")
                .flashAttr("user", userLogin))
                .andExpect(status().is3xxRedirection())
                .andExpect(MockMvcResultMatchers.redirectedUrl("/"));

        HashMap<String, Object> sessionattr = new HashMap<String, Object>();
        
        sessionattr.put("id", user.getId());
        sessionattr.put("email", user.getEmail());
        sessionattr.put("name", user.getName());
        sessionattr.put("loggedIn", true);

        mockMvc.perform(get("/")
                .sessionAttrs(sessionattr))
                .andExpect(status().isOk());
        
        mockMvc.perform(get("/feedback")
                .sessionAttrs(sessionattr))
                .andExpect(status().isOk());
        
        String description = "desc-" + RandomString.make(50).toLowerCase();
        
        SuggestModel suggestModel = new SuggestModel();
        suggestModel.setEmail_Suggest(email);
        suggestModel.setContent_Suggestion(description);
        suggestModel.setUser(user);
        
        mockMvc.perform(post("/feedback/store")
                .sessionAttrs(sessionattr)
                .flashAttr("suggestModel", suggestModel))
                .andExpect(status().is3xxRedirection())
                .andExpect(MockMvcResultMatchers.redirectedUrl("/"))
                .andDo(print());
    }
    
    @Test
    public void testCreateFeedBackNoEmail() throws Exception {

        String email = RandomString.make(10).toLowerCase() + "@gmail.com";
        String password = RandomString.make(10).toLowerCase();

        User user = new User();
        user.setEmail(email);
        user.setName("Test");
        user.setPassword(password);
        user.setAddress("disini");
        user.setCity("depok");
        user.setPhone("085921706170");

        mockMvc.perform(post("/register")
                .flashAttr("user", user))
                .andExpect(status().is3xxRedirection())
                .andExpect(MockMvcResultMatchers.redirectedUrl("/login"));

        User userLogin = new User();
        userLogin.setEmail(email);
        userLogin.setPassword(password);

        mockMvc.perform(post("/login")
                .flashAttr("user", userLogin))
                .andExpect(status().is3xxRedirection())
                .andExpect(MockMvcResultMatchers.redirectedUrl("/"));

        HashMap<String, Object> sessionattr = new HashMap<String, Object>();
        
        sessionattr.put("id", user.getId());
        sessionattr.put("email", user.getEmail());
        sessionattr.put("name", user.getName());
        sessionattr.put("loggedIn", true);

        mockMvc.perform(get("/")
                .sessionAttrs(sessionattr))
                .andExpect(status().isOk());
        
        mockMvc.perform(get("/feedback")
                .sessionAttrs(sessionattr))
                .andExpect(status().isOk());
        
        String description = "desc-" + RandomString.make(50).toLowerCase();
        
        SuggestModel suggestModel = new SuggestModel();
        suggestModel.setEmail_Suggest("");
        suggestModel.setContent_Suggestion(description);
        suggestModel.setUser(user);
        
        mockMvc.perform(post("/feedback/store")
                .sessionAttrs(sessionattr)
                .flashAttr("suggestModel", suggestModel))
                .andExpect(status().is3xxRedirection())
                .andExpect(MockMvcResultMatchers.redirectedUrl("/feedback"))
                .andExpect(MockMvcResultMatchers
                        .flash().attributeExists("danger")
                )
                .andExpect(
                        MockMvcResultMatchers
                                .flash()
                                .attribute("danger", "Suggestion or Email cannot be null!")
                );

    }
    
    @Test
    public void testCreateFeedBackNoSuggestion() throws Exception {

        String email = RandomString.make(10).toLowerCase() + "@gmail.com";
        String password = RandomString.make(10).toLowerCase();

        User user = new User();
        user.setEmail(email);
        user.setName("Test");
        user.setPassword(password);
        user.setAddress("disini");
        user.setCity("depok");
        user.setPhone("085921706170");

        mockMvc.perform(post("/register")
                .flashAttr("user", user))
                .andExpect(status().is3xxRedirection())
                .andExpect(MockMvcResultMatchers.redirectedUrl("/login"));

        User userLogin = new User();
        userLogin.setEmail(email);
        userLogin.setPassword(password);

        mockMvc.perform(post("/login")
                .flashAttr("user", userLogin))
                .andExpect(status().is3xxRedirection())
                .andExpect(MockMvcResultMatchers.redirectedUrl("/"));

        HashMap<String, Object> sessionattr = new HashMap<String, Object>();
        
        sessionattr.put("id", user.getId());
        sessionattr.put("email", user.getEmail());
        sessionattr.put("name", user.getName());
        sessionattr.put("loggedIn", true);

        mockMvc.perform(get("/")
                .sessionAttrs(sessionattr))
                .andExpect(status().isOk());
        
        mockMvc.perform(get("/feedback")
                .sessionAttrs(sessionattr))
                .andExpect(status().isOk());
        
        String description = "desc-" + RandomString.make(50).toLowerCase();
        
        SuggestModel suggestModel = new SuggestModel();
        suggestModel.setEmail_Suggest(email);
        suggestModel.setContent_Suggestion("");
        suggestModel.setUser(user);
        
        mockMvc.perform(post("/feedback/store")
                .sessionAttrs(sessionattr)
                .flashAttr("suggestModel", suggestModel))
                .andExpect(status().is3xxRedirection())
                .andExpect(MockMvcResultMatchers.redirectedUrl("/feedback"))
                .andExpect(MockMvcResultMatchers
                        .flash().attributeExists("danger")
                )
                .andExpect(
                        MockMvcResultMatchers
                                .flash()
                                .attribute("danger", "Suggestion or Email cannot be null!")
                );

    }
    
    @Test
    public void testCreateFeedBackNoBoth() throws Exception {

        String email = RandomString.make(10).toLowerCase() + "@gmail.com";
        String password = RandomString.make(10).toLowerCase();

        User user = new User();
        user.setEmail(email);
        user.setName("Test");
        user.setPassword(password);
        user.setAddress("disini");
        user.setCity("depok");
        user.setPhone("085921706170");

        mockMvc.perform(post("/register")
                .flashAttr("user", user))
                .andExpect(status().is3xxRedirection())
                .andExpect(MockMvcResultMatchers.redirectedUrl("/login"));

        User userLogin = new User();
        userLogin.setEmail(email);
        userLogin.setPassword(password);

        mockMvc.perform(post("/login")
                .flashAttr("user", userLogin))
                .andExpect(status().is3xxRedirection())
                .andExpect(MockMvcResultMatchers.redirectedUrl("/"));

        HashMap<String, Object> sessionattr = new HashMap<String, Object>();
        
        sessionattr.put("id", user.getId());
        sessionattr.put("email", user.getEmail());
        sessionattr.put("name", user.getName());
        sessionattr.put("loggedIn", true);

        mockMvc.perform(get("/")
                .sessionAttrs(sessionattr))
                .andExpect(status().isOk());
        
        mockMvc.perform(get("/feedback")
                .sessionAttrs(sessionattr))
                .andExpect(status().isOk());
        
        String description = "desc-" + RandomString.make(50).toLowerCase();
        
        SuggestModel suggestModel = new SuggestModel();
        suggestModel.setEmail_Suggest("");
        suggestModel.setContent_Suggestion("");
        suggestModel.setUser(user);
        
        mockMvc.perform(post("/feedback/store")
                .sessionAttrs(sessionattr)
                .flashAttr("suggestModel", suggestModel))
                .andExpect(status().is3xxRedirection())
                .andExpect(MockMvcResultMatchers.redirectedUrl("/feedback"))
                .andExpect(MockMvcResultMatchers
                        .flash().attributeExists("danger")
                )
                .andExpect(
                        MockMvcResultMatchers
                                .flash()
                                .attribute("danger", "Suggestion or Email cannot be null!")
                );

    }

}
