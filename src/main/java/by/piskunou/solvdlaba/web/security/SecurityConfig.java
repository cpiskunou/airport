package by.piskunou.solvdlaba.web.security;

import by.piskunou.solvdlaba.domain.User;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
//@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {

//    private final JwtAuthFilter jwtAuthFilter;
//    private final AuthenticationProvider authenticationProvider;
//
//    @Bean
//    public SecurityFilterChain securityFilterChain(HttpSecurity https) throws Exception {
//        return https.csrf().disable()
//                .authorizeHttpRequests().requestMatchers("/auth/*").permitAll()
//                .requestMatchers("/airlines/*",
//                        "/airplanes/*",
//                        "/city/*",
//                        "/country/*").hasRole(User.Role.ADMIN.name())
//                .requestMatchers("/{id}/*",
//                                           "/search").hasAnyRole(User.Role.ADMIN.name(),
//                                                                        User.Role.USER.name())
//                .anyRequest().authenticated()
//                .and()
//                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
//                .and()
//                .authenticationProvider(authenticationProvider)
//                .addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class)
//                .build();
//    }

}
