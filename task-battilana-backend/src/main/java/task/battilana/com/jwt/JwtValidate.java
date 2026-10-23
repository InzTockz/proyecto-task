package task.battilana.com.jwt;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import task.battilana.com.security.CustomDetailService;

import static task.battilana.com.jwt.Constans.*;

public class JwtValidate {

    public static boolean tokenExists(HttpServletRequest request, HttpServletResponse response){
        String header = request.getHeader(HEADER_AUTHORIZATION);
        if(header == null || !header.startsWith(TOKEN_BEARER_PREFIX)){
            return false;
        } else {
            return true;
        }
    }

    public static Claims jwtValid(HttpServletRequest request){
        String jwttoken = request.getHeader(HEADER_AUTHORIZATION).replace(TOKEN_BEARER_PREFIX, "");

        Claims claims = Jwts.parser()
                .verifyWith(getSignedKey(SUPER_SECRET_KEY))
                .build()
                .parseSignedClaims(jwttoken)
                .getPayload();
        return claims;
    }

    public static void setAuthentication(Claims claims, CustomDetailService customDetailService){
        UserDetails userDetails = customDetailService.loadUserByUsername(claims.getSubject());

        UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(
                userDetails, null, userDetails.getAuthorities()
        );

        SecurityContextHolder.getContext().setAuthentication(token);
    }
}
