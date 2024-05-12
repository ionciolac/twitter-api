package com.ionciolac.common.config.security


import io.swagger.v3.oas.annotations.OpenAPIDefinition
import io.swagger.v3.oas.annotations.enums.SecuritySchemeIn
import io.swagger.v3.oas.annotations.enums.SecuritySchemeType
import io.swagger.v3.oas.annotations.info.Info
import io.swagger.v3.oas.annotations.security.SecurityScheme
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.http.HttpMethod
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.web.SecurityFilterChain
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter

@OpenAPIDefinition(info = @Info(title = "twitter API", version = "2.0"))
@SecurityScheme(name = "twitter-api", scheme = "basic", type = SecuritySchemeType.HTTP, in = SecuritySchemeIn.HEADER)
@EnableWebSecurity
@Configuration
class SecurityConfig {

    CustomBasicAuthenticationFilter customBasicAuthenticationFilter
    String[] PATTERNS_PATH = [
            "/swagger-resources/",
            "/swagger-resources/**",
            "/swagger-ui/",
            "/swagger-ui/**",
            "/v3/api-docs/**",
            "/webjars/",
            "/webjars/**",
            "/api/swagger.json"
    ]

    SecurityConfig(CustomBasicAuthenticationFilter customBasicAuthenticationFilter) {
        this.customBasicAuthenticationFilter = customBasicAuthenticationFilter
    }

    @Bean
    SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.csrf(htpSecurity -> htpSecurity.disable())
        http.authorizeHttpRequests {
            it
                    .requestMatchers(PATTERNS_PATH).permitAll()
                    .requestMatchers(HttpMethod.POST, "/user").permitAll()
                    .anyRequest().authenticated()

        }.addFilterBefore(customBasicAuthenticationFilter, UsernamePasswordAuthenticationFilter.class)
        return http.build()
    }

}
