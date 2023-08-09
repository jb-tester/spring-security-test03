package com.mytests.spring.securitytest03;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import static org.springframework.security.config.Customizer.withDefaults;

/**
 * *
 * <p>Created by irina on 12/9/2021.</p>
 * <p>Project: spring-security-test03</p>
 * *
 */
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public UserDetailsService users() {

        User.UserBuilder users = User.withDefaultPasswordEncoder();
        UserDetails guest = users
                .username("guest")
                .password("jolt")
                .roles("USER")
                .build();
        UserDetails user = users
                .username("me")
                .password("jolt")
                .roles("VIP")
                .build();
        UserDetails admin = users
                .username("admin")
                .password("jolt")
                .roles("USER", "ADMIN", "VIP")
                .build();

        return new InMemoryUserDetailsManager(user, admin, guest);
    }
/*

    @Configuration
    @Order(1)
    public static class Path1SecurityConfigurationAdapter extends WebSecurityConfigurerAdapter {

        protected void configure(HttpSecurity http) throws Exception {
            http
                    .antMatcher("/path1/**")   // no reference here https://youtrack.jetbrains.com/issue/IDEA-286466
                    .authorizeHttpRequests(
                            authorize -> authorize
                                    .anyRequest().hasRole("ADMIN")  // no reference since authorizeHttpRequests() is used - fixed
                    )
                    .httpBasic(withDefaults());
        }
    }

    @Configuration
    @Order(2)
    public static class Path11SecurityConfigurationAdapter extends WebSecurityConfigurerAdapter {

        protected void configure(HttpSecurity http) throws Exception {
            http

                    .mvcMatcher("/path11/**") // no reference here https://youtrack.jetbrains.com/issue/IDEA-286467
                    .authorizeHttpRequests(
                            authorize -> authorize
                                    .anyRequest().hasAnyAuthority("ROLE_VIP", "ROLE_ADMIN")  // no reference since authorizeHttpRequests() is used - fixed
                    )
                    .httpBasic(withDefaults());
        }
    }
*/
/*

    @Configuration
    public static class PathsWebSecurityConfigurerAdapter extends WebSecurityConfigurerAdapter {
        @Bean
        public SecutityUtil secutityUtil() {
            return new SecutityUtil();
        }

        @Override
        protected void configure(HttpSecurity http) throws Exception {
            http
                    .authorizeRequests()
                    .antMatchers("/", "/actuator").permitAll()
                    .antMatchers(HttpMethod.GET, "/path3/**").access("hasAnyAuthority('ROLE_VIP','ROLE_ADMIN')")
                    .regexMatchers("/path2.*").access("hasRole('ADMIN')") // reference
                    .antMatchers("/secure").access("hasRole('ADMIN') or hasRole('VIP')") // roles are not underlined - fixed
                    .antMatchers("/user/{uid}").access("@secutityUtil.checkUser(authentication,#uid)") // pathvar is not resolved
                    .and()
                    .formLogin(withDefaults());
        }
    }
*/

    ;
    /*@Configuration
    public static class SecretRegexpSecurityConfigurationAdapter extends WebSecurityConfigurerAdapter {

        protected void configure(HttpSecurity http) throws Exception {
            http
                    .regexMatcher("/secret\\d[\\d]*") // no regexp here https://youtrack.jetbrains.com/issue/IDEA-286469 - fixed
                    .authorizeHttpRequests(
                            authorize -> authorize
                                    .anyRequest().hasRole("ADMIN")
                    )
                    .httpBasic(withDefaults());
        }
    }*/

   /* @Configuration
    public static class TestWebSecurityConfigurerAdapter extends WebSecurityConfigurerAdapter {

        @Override
        protected void configure(HttpSecurity http) throws Exception {
            http
                    .authorizeRequests()
                    .antMatchers("/","/actuator").permitAll()
                    .mvcMatchers("/test1/**").access("hasAnyAuthority('ROLE_VIP','ROLE_ADMIN')")
                    .mvcMatchers("/test2").hasRole("ADMIN")
                    .mvcMatchers("/test3").access("hasRole('ADMIN') or hasRole('VIP')")

                    .and()
                    .formLogin(withDefaults());
        }
    }*/

    @Configuration
    public static class PathsWebSecurityConfigurerAdapter extends WebSecurityConfigurerAdapter {
        private final MyFilter myFilter;

        public PathsWebSecurityConfigurerAdapter(MyFilter myFilter) {
            this.myFilter = myFilter;
        }

        @Bean
        public SecutityUtil secutityUtil() {
            return new SecutityUtil();
        }
        @Override
        protected void configure(HttpSecurity http) throws Exception {
            http
                    .authorizeRequests()
                    .antMatchers("/","/actuator").permitAll()
                    .antMatchers(HttpMethod.GET, "/path3/**").access("hasAnyAuthority('ROLE_VIP','ROLE_ADMIN')")
                    .regexMatchers("/path2.*").access("hasRole('ADMIN')") // reference
                    .antMatchers("/secure").access("hasRole('ADMIN') or hasRole('VIP')") // roles are not underlined - fixed
                    .antMatchers("/user/{uid}").access("@secutityUtil.checkUser(authentication,#uid)") // pathvar is not resolved
                    .and()
                    .addFilterAfter(myFilter, BasicAuthenticationFilter.class)
                    .formLogin(withDefaults());
        }
    }

}
