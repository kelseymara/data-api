package com.webage;

import static org.assertj.core.api.Assertions.assertThat;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.net.URI;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.security.oauth2.jwt.JwtClaimsSet;
import org.springframework.security.oauth2.jwt.JwtEncoder;
import org.springframework.security.oauth2.jwt.JwtEncoderParameters;
import org.springframework.security.oauth2.jwt.NimbusJwtEncoder;
import org.springframework.test.context.TestPropertySource;

import com.nimbusds.jose.jwk.JWK;
import com.nimbusds.jose.jwk.JWKSet;
import com.nimbusds.jose.jwk.RSAKey;
import com.nimbusds.jose.jwk.source.ImmutableJWKSet;
import com.nimbusds.jose.jwk.source.JWKSource;
import com.nimbusds.jose.proc.SecurityContext;

@SpringBootTest(webEnvironment=WebEnvironment.RANDOM_PORT)
@TestPropertySource(properties = { "rsa.private-key=classpath:keys/testPrivate.pem", "rsa.public-key=classpath:keys/testPublic.pem" })
@SuppressWarnings("rawtypes")
public class CustomerAPITests{
	
	@Autowired TestRestTemplate template;
	
	@Test
    //@Disabled
    void rootWhenUnauthenticatedThen401() throws Exception {
        //  GET list of customers without passing in any Authentication.
        //  We expect a 401 unauthorized failure. 
        RequestEntity request = RequestEntity
            .get("/api/customers")
            .build();

        ResponseEntity<String> responseEntity =
            template.exchange(request,String.class);

        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.UNAUTHORIZED);
    }

	   @Test
    //@Disabled
    public void rootWhenValidJwtThen200() {
        // Create a JWT token that represents a user with permission to read data
        String token = generateToken("user", "read");

        // Create a GET request to the /api/customers endpoint with the Authorization header containing the JWT
        RequestEntity request = RequestEntity
            .get("/api/customers") 
            .header("Authorization","Bearer " + token) // Add the token to the request header
            .build();

            ResponseEntity<String> responseEntity =
                template.exchange(request,String.class);

        //  We expect 200 OK when we provide valid credentials:
        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);
    }


    //  Generate a JWT for the given user and scope, signed with a private key.
    //  Note: this is essentially the same token generation logic provided by the Authorization Server.
    //  Also note: Ordinarily the resource server does not have access to
    //  the Auth Server's private key; we do so here only for testing.
    private String generateToken(String user, String scope) {
        Instant now = Instant.now();
        JwtClaimsSet claims = JwtClaimsSet.builder()
                .issuer("self")
                .issuedAt(now)
                .expiresAt(now.plus(1, ChronoUnit.HOURS))
                .subject(user)
                .claim("scope", scope)
                .build();
        return encoder().encode(JwtEncoderParameters.from(claims)).getTokenValue();
    }

    // Injects the private key from testPrivate.pem, used for signing JWTs
    @Value("${rsa.private-key}") RSAPrivateKey privateKey;
    // Injects the RSA public key from testPublic.pem, used for verifying JWTs
    @Value("${rsa.public-key}") RSAPublicKey publicKey;

    private JwtEncoder encoder() {
        JWK jwk = new RSAKey.Builder(publicKey).privateKey(privateKey).build();
        JWKSource<SecurityContext> jwks = new ImmutableJWKSet<>(new JWKSet(jwk));
        return new NimbusJwtEncoder(jwks); // 
    }
}