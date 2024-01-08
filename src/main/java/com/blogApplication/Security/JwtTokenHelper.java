//package com.blogApplication.Security;
//
//import io.jsonwebtoken.Claims;
//import io.jsonwebtoken.Jwts;
//import io.jsonwebtoken.SignatureAlgorithm;
//import org.springframework.cglib.core.internal.Function;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.stereotype.Component;
//
//import java.util.Date;
//import java.util.HashMap;
//import java.util.Map;
//
//import static javax.crypto.Cipher.SECRET_KEY;
//
//@Component
//public class JwtTokenHelper {
//
//    public static final long JWT_TOKEN_VALIDITY=5*60*60;
//
//    private String secret="JwtTokenKey";
//
//    // Retrieve username form jwt token
//
//    public String getUsernameFormToken(String token){
//
//        return getClaimFormToken(token , Claims::getSubject);
//    }
//    public Date getExpirationDateFromToken(String token){
//        return getClaimFormToken(token,Claims::getExpiration);
//    }
//    // for retrieving any information from token we will need the secret key
//
////
//    private Claims getAllClaimsFromToken(String token) {
////    return Jwts.parser().setSigningKey(secret).parseClaimsJws(token).getBody();
//        return Jwts.parser().setSigningKey(secret).build().parseClaimsJws(token).getBody();
//}
//
//    public <T> T getClaimFormToken(String token, Function<Claims,T> claimsResover){
//        final  Claims claims=getAllClaimsFromToken(token);
//        return claimsResover.apply(claims);
//    }
//
//    // Check Token is expired or not
//
//    private boolean isTokenExpired(String token){
//        final Date expiration=getExpirationDateFromToken(token);
//        return expiration.before(new Date());
//    }
//    //Generate Token form user
//
//    public String generateToken(UserDetails userDetails){
//        Map<String,Object> claims=new HashMap<>();
//        return doGenerateToken(claims,userDetails.getUsername());
//    }
//
//    private String doGenerateToken(Map<String, Object> claims, String subject) {
//        // Set the expiration time (e.g., 10 days)
//        Date expirationDate = new Date(System.currentTimeMillis() + 10 * 24 * 60 * 60 * 1000);
//
//        // Build the JWT
//        return Jwts.builder()
//                .setClaims(claims)
//                .setSubject(subject)
//                .setIssuedAt(new Date())
//                .setExpiration(expirationDate)
//                .signWith(SignatureAlgorithm.HS256, secret)
//                .compact();
//    }
//
//    // Validate Token
//    public boolean validateToken(String token,UserDetails userDetails){
//        final String username=getUsernameFormToken(token);
//        return (username.equals(userDetails.getUsername()) && !isTokenExpired(token));
//    }
//}
