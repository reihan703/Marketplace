package com.Project1.Project1Market;
        
import com.Project1.Project1Market.models.BuyProduct;
import com.Project1.Project1Market.models.User;
import java.util.HashMap;
import java.util.Random;
import net.bytebuddy.utility.RandomString;
import static org.hamcrest.Matchers.containsString;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mock.web.MockHttpSession;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.multipart.MultipartFile;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@AutoConfigureMockMvc
public class WebMvcBuyProductTests{
    
    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private WebApplicationContext webApplicationContext;
    
    @Test
    public void testRegisterThenPostBuyProduct() throws Exception {
        MockHttpSession session = new MockHttpSession();
        
        Random rnd = new Random();
        String SALTCHARS = "1234567890";
        StringBuilder salt = new StringBuilder();
        while (salt.length() < 14) { // length of the random string.
            int index = (int) (rnd.nextFloat() * SALTCHARS.length());
            salt.append(SALTCHARS.charAt(index));
        }
        String saltStr = salt.toString();
        
        mockMvc.perform(get("/register"))
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("Have an account?")));

        String email = RandomString.make(10).toLowerCase() + "@gmail.com";
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
        
        session.setAttribute("id", user.getId());
        session.setAttribute("phone", user.getPhone());
        session.setAttribute("city", user.getCity());
        session.setAttribute("address", user.getAddress());
        session.setAttribute("email", user.getEmail());
        session.setAttribute("name", user.getName());
        session.setAttribute("password", user.getPassword());
        session.setAttribute("loggedIn", true);
        
        mockMvc.perform(post("/login")
                .flashAttr("user", userLogin))
                .andExpect(status().is3xxRedirection())
                .andExpect(MockMvcResultMatchers.redirectedUrl("/"))
                .andDo(print());
        
        
        MockHttpServletRequestBuilder builder = MockMvcRequestBuilders.get("/")
                                        .session(session);
        
        mockMvc.perform(builder)
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("Want to Buy")));
        
        MockHttpServletRequestBuilder builder2 = MockMvcRequestBuilders.get("/profile")
                                        .session(session);
        
        mockMvc.perform(builder2)
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("Full Name")));
        
        MockHttpServletRequestBuilder builder3 = MockMvcRequestBuilders.get("/createBuy")
                                        .session(session);
        
        mockMvc.perform(builder3)
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("Foto Barang")));
        
        
        MultipartFile mmf = new MockMultipartFile("file", "test-file.txt",
                "text/plain" , "Green Learner - Arvind".getBytes());
        
        BuyProduct buyProduct = new BuyProduct();
        
        buyProduct.setImage(mmf.getOriginalFilename());
        buyProduct.setItem_Desc("test");
        buyProduct.setItem_Name("test2");
        buyProduct.setItem_Price(2000);
        buyProduct.setUser(user);
        
        String desc = "test";
        String name = "test";
        String price = "2000";
        
        long id = user.getId();
        
        MockMvc mockMvc2 
      = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
        mockMvc2.perform(MockMvcRequestBuilders.multipart("/addPB")
                .file("image",mmf.getBytes())
                .param("desc",desc)
                .param("pname",name)
                .param("price",price)
                .param("id_user",Long.toString(id)))
                .andExpect(status().is3xxRedirection())
                .andExpect(MockMvcResultMatchers.redirectedUrl("/profile"))
                .andDo(print());
    }
    
    @Test
    public void testLoginThenUpdate() throws Exception{
        
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
        
        mockMvc.perform(get("/buyProduct/4/edit")
                .sessionAttrs(sessionattr))
                .andExpect(status().isOk());
        
        MultipartFile mmf = new MockMultipartFile("file", "test-file.txt",
                "text/plain" , "Green Learner - Arvind".getBytes());
        
        String desc = "test";
        String name = "test";
        String priceProduct = "2000";
        long id_Buy = 4;
        long id = user.getId();
        
        MockMvc mockMvc2 
      = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
        mockMvc2.perform(MockMvcRequestBuilders.multipart("/updatePB")
                .file("image",mmf.getBytes())
                .param("item_Desc",desc)
                .param("item_Name",name)
                .param("item_Price",priceProduct)
                .param("id_user",Long.toString(id))
                .param("id_Buy",Long.toString(id_Buy)))
                .andExpect(status().is3xxRedirection())
                .andExpect(MockMvcResultMatchers.redirectedUrl("/profile"))
                .andDo(print());
    }
}
