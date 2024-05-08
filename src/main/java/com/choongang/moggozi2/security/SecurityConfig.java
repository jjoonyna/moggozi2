package com.choongang.moggozi2.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
	
	@Bean
	public BCryptPasswordEncoder bCryptPasswordEncoder() {

	    return new BCryptPasswordEncoder();
	}
	
    
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
            .authorizeRequests((auth) -> auth
            	.antMatchers("/", "/main", "/joinProc","/loginAdmin","/signup","/snslogin","/google_login","/google_login-callback","/naver_login","/naver_login-callback","/kakao_login","/kakao_login-callback","/mkjsignup","/id_pw_find","id_ok_result","pw_ok_result","/id_ok","/pw_ok","/sendPasswordByEmail","/sendUserByEmail").permitAll()
                .antMatchers("/search/**").permitAll() // 검색 기능에 대한 모든 사용자 접근 허용
                .antMatchers("/upload/**").permitAll() // 업로드된 파일에 대한 접근 권한 허용
                .antMatchers("/css/**","/js/**","/images/**","/icon/**").permitAll()
                .antMatchers("/admin/**").hasRole("ADMIN")
                .antMatchers("/my/**").hasAnyRole("ADMIN", "USER")
                .anyRequest().authenticated()
            );
        http
        
            .formLogin((auth) -> auth
                .loginPage("/signup")
                .loginProcessingUrl("/loginProc")
                .defaultSuccessUrl("/main", true)
                .permitAll()
            );
        http
        	.logout((logout) -> logout
                .logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
                .logoutSuccessUrl("/main")
                .invalidateHttpSession(true));
        http
            .csrf((auth) -> auth
                .disable()
            );


        return http.build();
    }
     
}
