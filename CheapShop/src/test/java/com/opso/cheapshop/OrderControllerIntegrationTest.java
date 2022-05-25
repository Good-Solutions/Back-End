package com.opso.cheapshop;

import org.json.JSONException;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.skyscreamer.jsonassert.JSONAssert;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;



@RunWith(SpringRunner.class)
@SpringBootTest(classes = CheapshopApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class OrderControllerIntegrationTest {
	
	@LocalServerPort
	private int port;

	TestRestTemplate restTemplate = new TestRestTemplate();

	HttpHeaders headers = new HttpHeaders();

	@Test
	public void TestGetOrderbyId() throws JSONException {

		HttpEntity<String> entity = new HttpEntity<String>(null, headers);

		ResponseEntity<String> response = restTemplate.exchange(createURLWithPort("/api/orders/1"),
				HttpMethod.GET, entity, String.class);

		String expected = 
				"{\"id\":1,\"purchase_date\":\"2025-12-19T00:41:10.000+00:00\",\"delivery_address\":\"larco 1449\",\"delivery_date\":\"2034-09-24 10:41:26\",\"quantity\":4}";

		JSONAssert.assertEquals(expected, response.getBody(), false);
	}

		
	private String createURLWithPort(String uri) {
		return "http://localhost:" + port + uri;
	}
	
	
	
	
}
