package org.dotwebstack.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.csrf.CsrfFilter;

@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

  @Autowired
  private UserDetailsService userDetailsService;

  @Autowired
  private UserRepository userRepository;

  @Override
  protected void configure(HttpSecurity http) throws Exception {
    for (UserRepository.ProtectedPath path : userRepository.getProtect()) {
      http.authorizeRequests().antMatchers(path.getPath()).hasAuthority(path.getRole());
    }
    String authentication = userRepository.getAuthentication();
    if (authentication != null) {
      if (authentication.equals("basic")) {
        http
          .authorizeRequests()
            .and()
          .httpBasic();
      }
      if (authentication.equals("form")) {
        String loginPage = userRepository.getLoginPage();
        if ((loginPage == null) || loginPage.equals("")) {
          loginPage = "/login";
        }
        CsrfTokenResponseHeaderBindingFilter csrfTokenFilter =
            new CsrfTokenResponseHeaderBindingFilter();
        http
          .authorizeRequests()
            .and()
          .csrf().disable()
          //For now: disable crsf (login page works OK, but PUT/POST give 405 in such cases :-()
          .formLogin()
            .loginPage(loginPage);
          //to expose the _csrf value in the reponse field
          //http.addFilterAfter(csrfTokenFilter, CsrfFilter.class);
      }
    }
  }

  @Bean
  public PasswordEncoder encoder() {
    return new BCryptPasswordEncoder(11);
  }

  @Bean
  public DaoAuthenticationProvider authProvider() {
    final DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
    authProvider.setUserDetailsService(userDetailsService);
    PasswordEncoder encoder = encoder();
    authProvider.setPasswordEncoder(encoder);
    return authProvider;
  }

  @Autowired
  public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
    auth.authenticationProvider(authProvider());
  }
}