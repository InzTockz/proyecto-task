package task.battilana.com.jwt;

import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;

import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;

public class Constans {

    public static final String HEADER_AUTHORIZATION = "Authorization";
    public static final String TOKEN_BEARER_PREFIX = "Bearer ";
    public static final long TOKEN_EXPIRATION_TIME = 3600000;

    public static SecretKey getSignedKey(String secretKey){
        byte[] keyBites = secretKey.getBytes(StandardCharsets.UTF_8);
        return Keys.hmacShaKeyFor(keyBites);
    }
}
