package bookmyconsultation.ratingservice.security;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class JwtAuthenticationFilter extends OncePerRequestFilter {

    private static final Logger LOGGER = LoggerFactory.getLogger(JwtAuthenticationFilter.class);

    @Autowired
    private TokenProvider tokenProvider;

    @Autowired
    private UserDetailsServiceImpl userDetailsServiceImpl;

    @Override
    protected void doFilterInternal(HttpServletRequest req, HttpServletResponse res, FilterChain filterChain) throws ServletException, IOException {
        try{
            String jwt = req.getHeader("Authorization");
            if(!StringUtils.isEmpty(jwt) && tokenProvider.validateToken(jwt)) {
                LOGGER.info("Token is valid! Checking Roles");
                String userNameInToken = tokenProvider.getUserNameFromToken(jwt);
                UserPrincipal userPrincipal = userDetailsServiceImpl.loadUserByUsername(userNameInToken);
                LOGGER.info("Authorities: "+userPrincipal.getAuthorities());
                UsernamePasswordAuthenticationToken authenticationToken =
                        new UsernamePasswordAuthenticationToken(userPrincipal, null, userPrincipal.getAuthorities());
                SecurityContextHolder.getContext().setAuthentication(authenticationToken);
            }
        } catch(Exception ex) {
            LOGGER.error("Failed to validate the token and/or set authentication token in security context", ex);
        }

        filterChain.doFilter(req, res);
    }
}
