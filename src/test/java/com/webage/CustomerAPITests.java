package com.webage;

import static org.assertj.core.api.Assertions.assertThat;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.net.URI;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;


@SpringBootTest(webEnvironment=WebEnvironment.RANDOM_PORT)
public class CustomerAPITests{
	
	@Autowired TestRestTemplate template;
	
	@Test
   // @Disabled
    void rootWhenUnauthenticatedThen401() throws Exception {
        //  GET the root resource without passing in any Authentication.
        //  We expect a 401 unauthorized failure. 
        RequestEntity request = RequestEntity
            .get("/api/customers")
            .build();

        ResponseEntity<String> responseEntity =
            template.exchange(request,String.class);

        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.UNAUTHORIZED);
    }

	
}