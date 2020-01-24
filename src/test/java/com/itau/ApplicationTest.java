package com.itau;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Collections;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * 
 * @author Assert Solutions S.A.S
 *
 */
@RunWith(SpringRunner.class)
@Configuration
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
public class ApplicationTest {

    @Value("${server.port}")
    private String serverPort;
    
    @Value("${path.test}")
	private String path;

    @Autowired
    private TestRestTemplate restTemplate;
    
    private final String URL = "http://localhost:";
    
    private Logger logger = LoggerFactory.getLogger(ApplicationTest.class);

    @Test
    public void testHealtcheck() throws Exception {

        // Call the REST API
        ResponseEntity<String> response = restTemplate.getForEntity(URL + serverPort + "/healtcheck",String.class);
        logger.info("Mensaje: {}", response.getBody());
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
    }
    
    @Test
	public void testResponse422(){
		HttpHeaders headers = new HttpHeaders();
		headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON_UTF8));
		headers.add("requestUUID", "123456");
    	headers.add("dateTime", "2019-12-30T18:33:12");
    	headers.add("originatorName", "BancaMovil - APP PN");
    	headers.add("originatorType", "47");
    	headers.add("terminalId", "127.0.0.1");
    	HttpEntity<String> entity = new HttpEntity<>(headers); 	
    	ResponseEntity<String> response = restTemplate.exchange(URL + serverPort + path, HttpMethod.GET	,entity, String.class);
    	logger.info("Respuesta:{}", response.getBody());
    	assertThat(response.getStatusCodeValue()).isEqualByComparingTo(422);
	}

 

}