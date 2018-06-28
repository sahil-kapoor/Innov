package co.nz.equifax.configurations;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.BeanIds;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import co.nz.equifax.security.JwtAuthenticationEntryPoint;
import co.nz.equifax.security.JwtAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class WebSecurity extends WebSecurityConfigurerAdapter {


  @Autowired
  private UserDetailsService userDetailsService;
  
  @Autowired
  private JwtAuthenticationEntryPoint unauthorizedHandler;

  @Bean
  public JwtAuthenticationFilter jwtAuthenticationFilter() {
      return new JwtAuthenticationFilter();
  }
  
  @Bean(BeanIds.AUTHENTICATION_MANAGER)
  @Override
  public AuthenticationManager authenticationManagerBean() throws Exception {
      return super.authenticationManagerBean();
  }

  @Bean
  public PasswordEncoder passwordEncoder() {
      return new BCryptPasswordEncoder();
  }
  

  @Override
  protected void configure(HttpSecurity http) throws Exception {
      http
              .cors()
                  .and()
              .csrf()
                  .disable()
              .exceptionHandling()
                  .authenticationEntryPoint(unauthorizedHandler)
                  .and()
              .sessionManagement()
                  .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                  .and()
              .authorizeRequests()
                  .antMatchers("/",
                      "/favicon.ico",
                      "/**/*.png",
                      "/**/*.gif",
                      "/**/*.svg",
                      "/**/*.jpg",
                      "/**/*.html",
                      "/**/*.css",
                      "/**/*.js")
                      .permitAll().antMatchers(
                        HttpMethod.GET,"/", "/v2/api-docs",           // swagger
                        "/webjars/**",            // swagger-ui webjars
                        "/swagger-resources/**",  // swagger-ui resources
                        "/configuration/**",      // swagger configuration
                        "/*.html",
                        "/favicon.ico",
                        "/**/*.html",
                        "/**/*.css",
                        "/**/*.js"
                ).permitAll()
                      /*.antMatchers(HttpMethod.GET,"/users/**")
                      .permitAll()*/
                      .antMatchers("/users/**")
                      .permitAll()
                      .antMatchers("/h2-console/**")
                      .permitAll()
               
               
                  .anyRequest()
                      .authenticated();
      http.cors().and().csrf().disable().authorizeRequests().antMatchers("/**").permitAll();
      http.headers().frameOptions().disable();
      // Add our custom JWT security filter
      http.addFilterBefore(jwtAuthenticationFilter(), UsernamePasswordAuthenticationFilter.class);

  }

  
/*@Override
  protected void configure(HttpSecurity http) throws Exception {
    
    http.cors().and().csrf().disable().authorizeRequests().antMatchers("/**").permitAll();
    http.headers().frameOptions().disable();
  }*/

   @Override
   public void configure(AuthenticationManagerBuilder authenticationManagerBuilder) throws Exception {
          authenticationManagerBuilder
                  .userDetailsService(userDetailsService)
                  .passwordEncoder(passwordEncoder());
      }


}
