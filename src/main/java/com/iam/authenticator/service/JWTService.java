package com.iam.authenticator.service;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import com.iam.authenticator.model.Users;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;

import java.util.Map;
import java.util.function.Function;

import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;

import java.security.NoSuchAlgorithmException;
import java.util.Base64;
import java.util.Date;
import java.util.HashMap;


@Service
public class JWTService {
	
	private String key="";
	
	public JWTService() {
		KeyGenerator keyGen;
		try {
			keyGen = KeyGenerator.getInstance("HmacSHA256");
			SecretKey sk=keyGen.generateKey();
			key=Base64.getEncoder().encodeToString(sk.getEncoded());
		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
 
	public String generateToken(Users user) {
		Map <String,Object> claims=new HashMap<>();
		
		return Jwts.builder()
				.claims()
				.add(claims)
				.subject(user.getUsername())
				.issuedAt(new Date(System.currentTimeMillis()))
				.expiration(new Date(System.currentTimeMillis()+60+60))
				.and()
				.signWith(getKey())
				.compact();
	}

	private SecretKey getKey() {
		// TODO Auto-generated method stub
		byte[]  keyBytes=Decoders.BASE64.decode(key);
		return Keys.hmacShaKeyFor(keyBytes);
	}

	public String extractUserName(String token) {
		return extractClaim(token, Claims::getSubject);
	}

	private <T> T extractClaim(String token, Function<Claims, T> claimResolver) {
        final Claims claims = extractAllClaims(token);
        return claimResolver.apply(claims);
    }


	private Claims extractAllClaims(String token) {
	        return Jwts.parser()
	                .verifyWith(getKey())
	                .build()
	                .parseSignedClaims(token)
	                .getPayload();

	}

	public boolean vaildateToken(String token, UserDetails userDetails) {
		final String userName = extractUserName(token);
        return (userName.equals(userDetails.getUsername()) && !isTokenExpired(token));
	}
	
	 private boolean isTokenExpired(String token) {
	        return extractExpiration(token).before(new Date());
	    }

	    private Date extractExpiration(String token) {
	        return extractClaim(token, Claims::getExpiration);
	    }

	
}
