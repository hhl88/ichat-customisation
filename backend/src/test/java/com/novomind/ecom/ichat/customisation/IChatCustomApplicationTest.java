package com.novomind.ecom.ichat.customisation;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Optional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;

import com.novomind.ecom.ichat.customisation.core.interfaces.services.IChatUserManagementService;
import com.novomind.ecom.ichat.customisation.core.users.IChatUser;
import com.novomind.ecom.ichat.customisation.endpoints.IChatUserController;

@RunWith(SpringJUnit4ClassRunner.class)
@WebMvcTest(IChatUserController.class)
@ContextConfiguration(classes = IChatCustomApplication.class)
@WithMockUser
public class IChatCustomApplicationTest {

  @Autowired
  private MockMvc mockMvc;

  @MockBean
  private IChatUserManagementService userManagementService;

  @Test
  public void test() throws Exception {
    String email = "test@test.com";
    System.out.println(email);
    Optional<IChatUser> user = userManagementService.findIChatUserByEmail(email);
    System.out.println(user);
    when(userManagementService.findIChatUserByEmail(email)).thenReturn(null);
    this.mockMvc.perform(post("").param("email", email)).andDo(print()).andExpect(status().isOk());
  }

}
