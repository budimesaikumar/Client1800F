package com.app;

import static org.junit.Assert.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.net.URISyntaxException;
import java.nio.charset.StandardCharsets;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.client.RestTemplate;

import com.app.model.EndPointResponse;

@RunWith(SpringRunner.class)
@SpringBootTest
class Client1800FApplicationTests {

	private final String CLIENT_URL = "http://jsonplaceholder.typicode.com/posts";
	private final String COUNT_URL = "http://localhost:8081/Client1800F/getCount";
	private final String UNIQUEUSRID_URL = "http://localhost:8081/Client1800F/getUserId";
	private final String UNPDATE_URL = "http://localhost:8081/Client1800F/getUpdate";

	@Test
	public void testGetEmployeeListSuccess() throws URISyntaxException {
		RestTemplate restTemplate = new RestTemplate();

		HttpHeaders headers = new HttpHeaders();
		MediaType mediaType = new MediaType("application", "json", StandardCharsets.UTF_8);
		// headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON,MediaType.));
		HttpEntity<String> entity = new HttpEntity<String>(headers);
		ResponseEntity<String> responseObj = restTemplate.exchange(CLIENT_URL, HttpMethod.GET, entity, String.class);
		assertNotNull(responseObj);
		assertEquals(200, responseObj.getStatusCodeValue());
	}

	@Test
	public void testCountEndPoints() throws URISyntaxException {
		RestTemplate restTemplate = new RestTemplate();

		HttpHeaders headers = new HttpHeaders();
		MediaType mediaType = new MediaType("application", "json", StandardCharsets.UTF_8);
		// headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON,MediaType.));
		HttpEntity<String> entity = new HttpEntity<String>(headers);
		EndPointResponse[] responseObj = restTemplate
				.exchange(COUNT_URL, HttpMethod.GET, entity, EndPointResponse[].class).getBody();
		assertNotNull(responseObj);
		assertEquals(100, responseObj.length);
	}

	@Test
	public void testUniqueId() throws URISyntaxException {
		RestTemplate restTemplate = new RestTemplate();

		HttpHeaders headers = new HttpHeaders();
		MediaType mediaType = new MediaType("application", "json", StandardCharsets.UTF_8); //
		HttpEntity<String> entity = new HttpEntity<String>(headers);
		ResponseEntity<EndPointResponse[]> responseObj = restTemplate.exchange(UNIQUEUSRID_URL, HttpMethod.GET, entity,
				EndPointResponse[].class);
		assertEquals(200, responseObj.getStatusCodeValue());
		assertNotNull(responseObj);
	}

	@Test
	public void testUpdate() throws URISyntaxException {
		RestTemplate restTemplate = new RestTemplate();

		HttpHeaders headers = new HttpHeaders();
		MediaType mediaType = new MediaType("application", "json", StandardCharsets.UTF_8); //
		HttpEntity<String> entity = new HttpEntity<String>(headers);
		ResponseEntity<EndPointResponse[]> responseObj = restTemplate.exchange(UNPDATE_URL, HttpMethod.GET, entity,
				EndPointResponse[].class);
		assertEquals(200, responseObj.getStatusCodeValue());
		assertNotNull(responseObj);

	}

}
