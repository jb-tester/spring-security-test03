package com.mytests.spring.securitytest03;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.web.FilterChainProxy;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.user;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


/**
 * this test is for configuration
 * com.mytests.spring.securitytest03.SecurityConfig.PathsWebSecurityConfigurerAdapter
 */
@SpringBootTest
@RunWith(SpringRunner.class)
@WebAppConfiguration
public class PathsControllerTests {

    @Autowired
    private WebApplicationContext wac;

    @Autowired
    private FilterChainProxy springSecurityFilterChain;

    private MockMvc mockMvc;

    @Before
    public void setup() {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(this.wac).addFilter(springSecurityFilterChain).build();
    }

    @Test
    public void path3IsForAdminOrVipOnly() throws Exception {
        mockMvc.perform(get("/path3/foo")).andExpect(status().isFound());
        mockMvc.perform(get("/path3/foo").with(user("admin").password("jolt").roles("ADMIN"))).andExpect(status().isOk());
        mockMvc.perform(get("/path3/bar").with(user("me").password("jolt").roles("VIP"))).andExpect(status().isOk());
        mockMvc.perform(get("/path3").with(user("guest").password( "jolt").roles("USER"))).andExpect(status().isForbidden());


    }

    @org.junit.Test
    public void path2IsForAdminsOnly() throws Exception {

        mockMvc.perform(get("/path2").with(user("admin").password("jolt").roles("ADMIN"))).andExpect(status().isOk());
        mockMvc.perform(get("/path2").with(user("me").password("jolt").roles("VIP"))).andExpect(status().isForbidden());

    }

    @Test
    public void homeDoesNotNeedAuthentication() throws Exception {
        mockMvc.perform(get("/")).andExpect(status().isOk());
        mockMvc.perform(get("/actuator")).andExpect(status().isOk());


    }

}
