package com.example.spring.boot.project.Security;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.stereotype.Component;

import java.util.Date;
@Component
public class JwtUtil {
    private final String SECRET_KEY = "76e2e5bcb1a251caba6941b0934e835d248846a332c94d7743912fde4dba8e1cf9982db1556d12b87449a5384e5693077c5f9a268c2077c07177e7679fd98e573d7c9fa73211451f49ac50670939aa628c2f15bcf2884fb384e7629f8ce9a97c0860f82c7d47098faf2b05248f7dec23da53fa7f572f5d09599f2f75b376f812a29310673cb9c53ff8a7669b59ad00553106be654c30014f41b8ea843addc2e8874cb3589c78db55307d5aed7aceffc0fbb3aed9ba722bbf5118695184ec4f9279f8c634ef102fef22cd181e58a95623024431f09fb261a23a137acb86694d19f3d411dc1a3cf39f4e66d7ed0493e123c1a086795e7de985b8a6a4e69d24ed6d";

    public String generateToken(String username) {
        return Jwts.builder()
                .setSubject(username)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + 3600000)) // 1 hour
                .signWith(SignatureAlgorithm.HS256, SECRET_KEY)
                .compact();
    }

    public String extractUsername(String token) {
        return Jwts.parser()
                .setSigningKey(SECRET_KEY)
                .parseClaimsJws(token)
                .getBody()
                .getSubject();
    }

    public boolean validateToken(String token, String username) {
        return (username.equals(extractUsername(token)) && !isTokenExpired(token));
    }

    private boolean isTokenExpired(String token) {
        return Jwts.parser()
                .setSigningKey(SECRET_KEY)
                .parseClaimsJws(token)
                .getBody()
                .getExpiration()
                .before(new Date());
    }

}
