package com.example.task.Filter;

import com.example.task.Util.jwtUtil;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.List;

@Component
public class jwtFilter extends OncePerRequestFilter {

    @Autowired
    private jwtUtil jwtUtil;

    @Override
    protected void doFilterInternal(HttpServletRequest req, HttpServletResponse res, FilterChain filterChain) throws ServletException,IOException
    {
       String authHeader = req.getHeader("Authorization");
       if(authHeader != null && authHeader.startsWith("Bearer"))
       {
           String token = authHeader.substring(7);
           try
           {
               String userName = jwtUtil.extractUserName(token);
               if(userName != null && SecurityContextHolder.getContext().getAuthentication() == null)
               {
                   UsernamePasswordAuthenticationToken auth =
                           new UsernamePasswordAuthenticationToken(userName,null, List.of());
                   auth.setDetails(new WebAuthenticationDetailsSource().buildDetails(req));
                   SecurityContextHolder.getContext().setAuthentication(auth);
               }
           } catch (Exception e) {
               throw new RuntimeException(e);
           }
       }
       filterChain.doFilter(req,res);
    }
}
