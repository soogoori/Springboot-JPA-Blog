package com.lookie.demo.config;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;

// 빈 등록 : 스프링 컨테이너에서 객체를 관리할 수 있게 하는 것
@RequiredArgsConstructor
@Configuration // 빈등록(IoC관리)
@EnableWebSecurity // 시큐리티 필터가 등록이 됨 -> 설정을 해당 클래스에서 함
@EnableGlobalMethodSecurity(prePostEnabled = true) // 특정 주소로 접근하면 권한 및 인증을 미리 체크
public class SecurityConfig {

    @Autowired
    private PasswordEncoder passwordEncoder;
    private final AuthenticationFailureHandler customFailureHandler;

    /*@Bean // IoC가 됨
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }*/

    // 시큐리티가 대신 로그인해주는데 password를 가로챔
    // 해당 password가 뭘로 해쉬가 되어 회원가입이 되었는지 알아야
    // 같은 해쉬로 암호화해서 DB에 있는 해쉬랑 비교 가능

 /*   protected void configure(AuthenticationManagerBuilder auth) throws Exception{
        auth.userDetailsService(principalDetailService).passwordEncoder(passwordEncoder()); //principalDetailService에게 알려줘야 함
    }
*/
    /*@Autowired // 스프링이 알아서 인스턴스 주입
    private DataSource dataSource; // yml 파일에 있는 dataSource 사용 가능*/

    /*// JDBC 인증
    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
                auth.jdbcAuthentication()// 스프링 시큐리티가 실행 될 때 사용자를 생성하고, 애플리케이션이 종료되면 삭제하는 사용자
                    .dataSource(dataSource)
                    .withDefaultSchema()
                    .withUser("test") // 여기서의 User는 security에서 사용하는 것.
                            .password(passwordEncoder.encode("pass")) // 비밀번호 암호화
                            .roles("USER")
                            .and()
                    .withUser(User.withUsername("admin")
                        .password(passwordEncoder.encode("password"))
                        .roles("USER", "ADMIN"));
    }*/

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.csrf().disable() // csrf 토큰 비활성화 (테스트시 걸어두는 게 좋음)
                .authorizeHttpRequests()
                    .antMatchers("/account/join", "/", "/h2-console/**", "/css/**").permitAll() // 로그인 없이 접근 가능
                    .antMatchers("/admin/**").hasAnyRole("ADMIN")
                    .anyRequest().authenticated() // 그 외 요청은 반드시 로그인을 해야 접근 가능 -> 인증된 사용자만 접근 가능
                    .and()
                .formLogin()
                    .loginPage("/account/login").permitAll() // 다른 페이지에 가게 되면 /auth/loginForm 페이지로 자동 리다이렉트 -> 사용자가 만든 로그인 페이지 사용 -> 설정하지 않으면 기본 로그인 페이지 사용
                .failureHandler(customFailureHandler)
                .defaultSuccessUrl("/") // 정상적으로 인증 성공 했을 경우 이동하는 페이지
                    .and()
                .logout()
                    .logoutSuccessUrl("/")
                    .permitAll();

        return http.build();
    }


}
