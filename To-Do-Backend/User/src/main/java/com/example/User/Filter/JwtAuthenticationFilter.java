package com.example.User.Filter;

import com.example.User.Model.User;
import com.example.User.Service.UserService;
import com.example.User.Utils.JwtUtil;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.List;

@Component
@RequiredArgsConstructor
public class JwtAuthenticationFilter extends OncePerRequestFilter {
    private final JwtUtil jwtUtil;
    private final UserService userService;

    @Override
    protected void doFilterInternal(HttpServletRequest req, HttpServletResponse res,
                                    FilterChain filterChain) throws ServletException, IOException
    {
        String authHeader = req.getHeader("Authorization");
        if(authHeader != null && authHeader.startsWith("Bearer"))
        {
            String token = authHeader.substring(7);
            try
            {
                String userName = jwtUtil.extractUserName(token);
                if(userName!= null && SecurityContextHolder.getContext().getAuthentication() == null) {
                    User user = userService.findByUserName(userName);
                    if(user!= null)
                    {
                        UsernamePasswordAuthenticationToken auth =
                                new UsernamePasswordAuthenticationToken(userName,null, List.of());
                                auth.setDetails(new WebAuthenticationDetailsSource().buildDetails(req));
                                SecurityContextHolder.getContext().setAuthentication(auth);
                    }

                }

            }catch (Exception ex)
            {
                throw new RuntimeException(ex.getMessage());
            }
        }

        filterChain.doFilter(req,res);
    }
}
