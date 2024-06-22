package com.propertyservice.propertyservice.jwt;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.util.Base64;
import java.util.Date;
import java.util.regex.Pattern;

@Slf4j
@Component
@RequiredArgsConstructor
public class TokenProvider {

    @Value("${spring.jwt.secret}")
    private String secret;

   //key를 HMAC-SHA알고리즘을 사용하여 byte 배열로 decode함.
    private SecretKey getLoginKey(){
        byte[] keyBytes;
        if(checkEncoding()) {
            // Base64로 인코딩 되었을 때.
            keyBytes =  Decoders.BASE64.decode(this.secret);
        }
        else {
            // Base64로 인코딩 안되었을 때.
            String encodingSecret = encodingKey(this.secret);
            keyBytes = Decoders.BASE64.decode(encodingSecret);
        }
        return new SecretKeySpec(keyBytes, Jwts.SIG.HS256.key().build().getAlgorithm());
    }

    //base64 인코딩 check
    public boolean checkEncoding(){
        String encodedKey = encodingKey(this.secret);

        Pattern pattern = Pattern.compile("^([A-Za-z0-9+/]{4})*([A-Za-z0-9+/]{3}=|[A-Za-z0-9+/]{2}==)?$");
        return  pattern.matcher(encodedKey).find();
    }

   // key 인코딩
    public String encodingKey(String key){
        return Base64.getEncoder().encodeToString(key.getBytes());
    }

    // token을 통해 username 가져오기.
    public String getUsername(String token){
        return Jwts.parser().verifyWith(getLoginKey()).build().parseSignedClaims(token).getPayload().get("username", String.class);
    }
    // token을 통해 role 가져오기.
    public String getRole(String token){
        return Jwts.parser().verifyWith(getLoginKey()).build().parseSignedClaims(token).getPayload().get("role", String.class);
    }
    // token을 통해 만기일 가져오기.
    public Boolean isExpired(String token){
        return Jwts.parser().verifyWith(getLoginKey()).build().parseSignedClaims(token).getPayload().getExpiration().before(new Date());
    }


    public String generateJwtToken(String username, String role, Long expiredMs){
        return Jwts.builder()
                .claim("username", username) // username
                .claim("role", role) //권한.
                .issuedAt(new Date()) //생성일
                .expiration(new Date((new Date().getTime()+ expiredMs)))// 만료일
                .signWith(getLoginKey()) //key
                .compact();
    }
}
