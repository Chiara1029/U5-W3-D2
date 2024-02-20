package com.chiarapuleio.devicesmanagement.security;

import com.chiarapuleio.devicesmanagement.entities.Employee;
import com.chiarapuleio.devicesmanagement.exceptions.UnauthorizedException;
import com.chiarapuleio.devicesmanagement.services.EmployeeService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.neo4j.Neo4jProperties;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.util.AntPathMatcher;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.UUID;

@Component
public class JWTFilter extends OncePerRequestFilter {
    @Autowired
    private JWTTools jwtTools;
    @Autowired
    private EmployeeService empSrv;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException{
        String authHeader = request.getHeader("Authorization");
        if(authHeader == null || !authHeader.startsWith("Bearer ")) throw new UnauthorizedException("Token missing, please try again.");

        String accessToken = authHeader.substring(7);
        System.out.println("ACCESS TOKEN " + accessToken);

        String id = jwtTools.extractIdFromToken(accessToken);
        Employee employee = empSrv.findById(UUID.fromString(id));

        Authentication authentication = new UsernamePasswordAuthenticationToken(employee, null, employee.getAuthorities());
        SecurityContextHolder.getContext().setAuthentication(authentication);

        filterChain.doFilter(request, response);
    }

    @Override
    protected boolean shouldNotFilter(HttpServletRequest request){
        return new AntPathMatcher().match("/auth/**", request.getServletPath());
    }
}
