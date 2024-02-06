package com.example.loginwithmanuallycodedtoken.services;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.example.loginwithmanuallycodedtoken.models.User;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class JWTService {
    @Value("${jwt.userClaim}")
    private String userClaim;
    @Value("${jwt.userLoginIssuer}")
    private String issuer;
    @Value("${jwt.userLoginSecretKey}")
    private String secretKey;

//    public Algorithm genrateAlgorithm(){
//
//    }
    public String generateJwtToken(User user){
        return JWT.create()
                .withClaim(userClaim,user.getUsername())
                .withIssuer(issuer)
                .withExpiresAt(new Date(System.currentTimeMillis()+3600))
                .sign(Algorithm.HMAC256(secretKey));
    }

    public void validateJwtToken(String token){

    }

    public String getUser(String token){
        //verify() method in java verifies the token
        DecodedJWT decodedJWT = JWT.require(Algorithm.HMAC256(secretKey))
                .withIssuer(issuer)
                .build().verify(token);

        //Now extract the claim from the token
        return decodedJWT.getClaim(userClaim).asString();
    }
}
