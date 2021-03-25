package com.app.external.service;

import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.app.model.EndPointResponse;
import com.app.service.EndPointService;

@RestController
public class EndPointRestService {

	private final String CLIENT_URL = "http://jsonplaceholder.typicode.com/posts";

	@Autowired
	RestTemplate restTemplate;

	@Autowired
	EndPointService endPointService;

	@GetMapping("/getCount")
	public long countEndPoints() {
		long count = 0;

		EndPointResponse[] str = srviceCall();
		if (str != null) {
			count = str.length;
		}
		return count;

	}

	@GetMapping("/getUserId")
	public Map<Integer, List<EndPointResponse>> getUniqueId() {
		return endPointService.getUniqueId(srviceCall());
	}

	@GetMapping("/getUpdate")
	public Map<Integer, List<EndPointResponse>> update() {

		return endPointService.update(srviceCall());
	}

	private EndPointResponse[] srviceCall() {
		HttpHeaders headers = new HttpHeaders();
		MediaType mediaType = new MediaType("application", "json", StandardCharsets.UTF_8);
		// headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON,MediaType.));
		HttpEntity<String> entity = new HttpEntity<String>(headers);
		EndPointResponse[] responseObj = restTemplate
				.exchange(CLIENT_URL, HttpMethod.GET, entity, EndPointResponse[].class).getBody();
		return responseObj;
	}
}
