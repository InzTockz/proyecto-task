package task.battilana.com.jwt;

import io.jsonwebtoken.Jwts;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import static task.battilana.com.jwt.Constans.*;

@Service
public class JwtGenerator {

    public String getToken(String username){
        List<GrantedAuthority> authorityList = AuthorityUtils.commaSeparatedStringToAuthorityList(
                SecurityContextHolder.getContext().getAuthentication().getAuthorities()
                        .stream().findFirst().toString()
        );

        String token = Jwts.builder()
                .id("Task")
                .subject(username)
                .claim("authorities", authorityList.stream()
                        .map(GrantedAuthority::getAuthority).collect(Collectors.toList()))
                .issuedAt(new Date(System.currentTimeMillis()))
                .expiration(new Date(System.currentTimeMillis() + TOKEN_EXPIRATION_TIME))
                .signWith(getSignedKey(SUPER_SECRET_KEY))
                .compact();
        return "Bearer " + token;
    }
}
