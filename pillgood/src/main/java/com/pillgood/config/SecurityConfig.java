package com.pillgood.config;

import com.pillgood.security.CustomAuthenticationSuccessHandler;
import com.pillgood.service.CustomUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.Http403ForbiddenEntryPoint;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import static org.springframework.security.config.Customizer.withDefaults;

@Configuration
@EnableWebSecurity //Spring Security 활성화
public class SecurityConfig {

    // CustomUserDetailsService : 사용자의 세부 정보 로드
    private final CustomUserDetailsService customUserDetailsService;

    private final CustomAuthenticationSuccessHandler successHandler;

    public SecurityConfig(CustomUserDetailsService customUserDetailsService, CustomAuthenticationSuccessHandler successHandler) {
        this.customUserDetailsService = customUserDetailsService;
        this.successHandler = successHandler;
    }

    // 정적 리소스 "/resources/**", "/public/**" 에 대한 보안 무시
    @Bean
    public WebSecurityCustomizer webSecurityCustomizer() {
        return (web) -> web.ignoring().requestMatchers("/resources/**", "/public/**");
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .authorizeHttpRequests(authorize -> authorize
                        // 언급된 경로는 인증 없이 접근 가능
                        .requestMatchers("/login"
                                , "/css/**"
                                , "/images/**"
                                , "/js/**"
                                , "/members/register"
                                , "/members/login"
                                , "/admin/**"
                                , "/api/**"
                                , "/members/check-session").permitAll()
                        // 그 외 모든 요청은 인증 필요
                        .anyRequest().authenticated()
                )
                .formLogin(formLogin -> formLogin // 폼 기반 로그인 설정
                        // 로그인 페이지 url 설정
                        .loginPage("/members/login")
                        .loginProcessingUrl("/login")
                        .successHandler(successHandler) // 성공 핸들러 추가
                        .permitAll()
                        // 로그인 성공 시 기본 페이지 설정
                        .defaultSuccessUrl("/", true)
                        // 로그인 실패 시 리디렉션 rul
                        .failureUrl("/login?error=true")
                )
                .sessionManagement(session -> session // 세션 관리 설정
                        // 세션이 유효하지 않을 시 리디렉션 url
                        .invalidSessionUrl("/members/login")
                )
                .logout(logout -> logout
                        // 로그아웃 url
                        .logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
                        // 로그아웃 시 세션 무효화
                        .invalidateHttpSession(true)
                        // 로그아웃 시 쿠키 JSESSIONID 삭제
                        .deleteCookies("JSESSIONID")
                        .permitAll()
                )
                // 접근이 거부된 경우 403 응답 전송
                .exceptionHandling(exceptionHandling -> exceptionHandling
                        .authenticationEntryPoint(new Http403ForbiddenEntryPoint())
                )
                .cors(withDefaults()) // CORS 설정 추가
                .csrf().disable(); // CSRF 비활성화

        return http.build();
    }

    // 비밀번호 암호화 BCryptPasswordEncoder
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    // CORS(Cross-Origin Resource Sharing) 설정
    @Bean
    public WebMvcConfigurer corsConfigurer() {
        return new WebMvcConfigurer() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                // 모든 경로에 대해 8080(vue) 에서의 접근 허용
                registry.addMapping("/**")
                        .allowedOrigins("http://localhost:8080")
                        .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS")
                        .allowedHeaders("*")
                        .allowCredentials(true);
            }
        };
    }

}