package grameena.grameena_java_backend.security;

import grameena.grameena_java_backend.security.CustomUserDetails;
import grameena.grameena_java_backend.security.CustomUserDetailsService;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import java.io.IOException;

@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {
    private final JwtService jwtService;
    private final CustomUserDetailsService customUserDetailsService;

    public JwtAuthenticationFilter(
            JwtService jwtService,
            CustomUserDetailsService customUserDetailsService) {

        this.jwtService = jwtService;
        this.customUserDetailsService = customUserDetailsService;
    }
    @Override
    protected void doFilterInternal(
HttpServletRequest request,
            HttpServletResponse response,
            FilterChain filterChain)

            throws ServletException, IOException {


        String authHeader = request.getHeader("Authorization");

        if (authHeader == null || !authHeader.startsWith("Bearer ")) {

            filterChain.doFilter(request, response);
            return;
        }

        String jwt = authHeader.substring(7);
if (!jwtService.isTokenValid(jwt)) {
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            return;
        }
        String phoneNumber = jwtService.extractPhoneNumber(jwt);

        UserDetails userDetails =
                customUserDetailsService.loadUserByUsername(phoneNumber);

        UsernamePasswordAuthenticationToken authentication =
                new UsernamePasswordAuthenticationToken(
                        userDetails,
                        null,
                        userDetails.getAuthorities()
                );

        authentication.setDetails(
                new WebAuthenticationDetailsSource().buildDetails(request)
        );

        SecurityContextHolder.getContext().setAuthentication(authentication);
filterChain.doFilter(request, response);


    }
}

