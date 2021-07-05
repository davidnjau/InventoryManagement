package com.dave.inventorymanagement.authentication;

import com.dave.inventorymanagement.service.service_manager.UserSenderService;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.SignatureException;
import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.annotation.Resource;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class JwtAuthenticationFilter extends OncePerRequestFilter {

    @Value("${jwt.header.string}")
    public String HEADER_JWT_STRING;

    @Value("${access.token.header.string}")
    public String HEADER_ACCESS_STRING;

    @Value("${jwt.token.prefix}")
    public String TOKEN_PREFIX;

    @Resource(name = "userSenderService")
    private UserDetailsService userDetailsService;

    @Autowired
    private UserSenderService userService;

    @Autowired
    private TokenProvider jwtTokenUtil;

    @Override
    protected void doFilterInternal(HttpServletRequest req, HttpServletResponse res, FilterChain chain) throws IOException, ServletException {
        
        //Get Jwt Parameters
        String header_jwt = req.getHeader(HEADER_JWT_STRING);
        String header_access_token = req.getHeader(HEADER_ACCESS_STRING);
        
        String username = null;
        String jwtToken = null;
        String authToken = null;
        boolean isAccessToken = false;

        if (header_jwt != null && header_jwt.startsWith(TOKEN_PREFIX) &&
                header_access_token != null && header_access_token.startsWith(TOKEN_PREFIX)) {
            
            jwtToken = header_jwt.replace(TOKEN_PREFIX,"");
            authToken = header_access_token.replace(TOKEN_PREFIX,"").substring(1);

            try {
                username = jwtTokenUtil.getUsernameFromToken(jwtToken);
                String userId = String.valueOf(userService.findUserByUsername(username).getId());

                String hashedAccessToken = DigestUtils.sha256Hex(userId + username);
                isAccessToken = authToken.equals(hashedAccessToken);

            } catch (IllegalArgumentException e) {
                logger.error("An error occurred while fetching Username from Token", e);
            } catch (ExpiredJwtException e) {
                logger.warn("The token has expired", e);
            } catch(SignatureException e){
                logger.error("Authentication Failed. Username or Password not valid.");
            }
        } else {
            logger.warn("Couldn't find bearer string, header will be ignored");
        }
        if (username != null && SecurityContextHolder.getContext().getAuthentication() == null && isAccessToken) {

            UserDetails userDetails = userDetailsService.loadUserByUsername(username);

            if (jwtTokenUtil.validateToken(jwtToken, userDetails)) {

                UsernamePasswordAuthenticationToken authentication = jwtTokenUtil.getAuthenticationToken(jwtToken,
                        SecurityContextHolder.getContext().getAuthentication(), userDetails);
                authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(req));
                logger.info("authenticated user " + username + ", setting security context");
                SecurityContextHolder.getContext().setAuthentication(authentication);

            }
        }

        chain.doFilter(req, res);
    }
}
