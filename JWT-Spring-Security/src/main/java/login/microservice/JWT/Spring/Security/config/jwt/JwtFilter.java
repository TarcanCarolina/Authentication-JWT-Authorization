package login.microservice.JWT.Spring.Security.config.jwt;

import login.microservice.JWT.Spring.Security.config.CustomUserDetails;
import login.microservice.JWT.Spring.Security.config.CustomUserDetailsService;
import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.GenericFilterBean;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

import static org.springframework.util.StringUtils.hasText;

@Component
@Log
public class JwtFilter extends GenericFilterBean {

    public static final String AUTHORIZATION = "Authorization";

    @Autowired
    private JwtProvider jwtProvider;

    @Autowired
    private CustomUserDetailsService customUserDetailsService;

    /**
     * Every request have to be filtered because it isn't permitted the access if the user
     * doesn't be logged.
     * At authorization is generated a token that it is must be validate to give access.
     */
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
       //logger.info("do filter...");
        String token = getTokenFromRequest((HttpServletRequest) servletRequest);
        logger.info("do filter...valid = "+jwtProvider.validateToken(token));
        logger.info("do filter...token = "+token);
        if (token != null && jwtProvider.validateToken(token)) {
            logger.info("do filter...valid in if"+jwtProvider.validateToken(token));

            String userLogin = jwtProvider.getLoginFromToken(token);
            logger.info("userLogin = "+userLogin);
            CustomUserDetails customUserDetails = customUserDetailsService.loadUserByUsername(userLogin);
            UsernamePasswordAuthenticationToken auth = new UsernamePasswordAuthenticationToken(customUserDetails, null, customUserDetails.getAuthorities());
            logger.info("auth = "+ auth.getAuthorities());
            SecurityContextHolder.getContext().setAuthentication(auth);

        }
        filterChain.doFilter(servletRequest, servletResponse);
    }

    /**
     * Extract token from the request
     */
    private String getTokenFromRequest(HttpServletRequest request) {
        String bearer = request.getHeader(AUTHORIZATION);
        if (hasText(bearer) && bearer.startsWith("Bearer ")) {
            return bearer.substring(7);
        }
        return null;
    }
}
