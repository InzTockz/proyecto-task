package task.battilana.com.jwt;

import io.jsonwebtoken.security.Keys;

import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;

public class Constans {

    public static final String HEADER_AUTHORIZATION = "Authorization";
    public static final String TOKEN_BEARER_PREFIX = "Bearer ";
    public static final String SUPER_SECRET_KEY = "d3DOdZvlu6QUgkZhJ8IFPnE0qDGCNODOG7d27BQ2FpR4kSdnJ0PouFCqUN3Kobs89FlbbUuHvZ2";
    public static final long TOKEN_EXPIRATION_TIME = 3600000;

    public static SecretKey getSignedKey(String secretKey){
        byte[] keyBites = secretKey.getBytes(StandardCharsets.UTF_8);
        return Keys.hmacShaKeyFor(keyBites);
    }
}
