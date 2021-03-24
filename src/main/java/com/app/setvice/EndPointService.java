package com.app.setvice;

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

@RestController
public class EndPointService {

	public final String CLIENT_URL = "http://jsonplaceholder.typicode.com/posts";

	@Autowired
	RestTemplate restTemplate;

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

		EndPointResponse[] str = srviceCall();
		Map<Integer, List<EndPointResponse>> response = null;
		if (str != null) {
			response = Arrays.asList(str).stream().collect(Collectors.groupingBy(EndPointResponse::getUserId));
		}
		return response;

	}

	@GetMapping("/getUpdate")
	public Map<Integer, List<EndPointResponse>> update() {

		EndPointResponse[] str = srviceCall();
		Map<Integer, List<EndPointResponse>> response = null;
		if (str != null) {
			response = Arrays.asList(str).stream().collect(Collectors.groupingBy(EndPointResponse::getUserId));
			Object key = 4;
			ArrayList<EndPointResponse> list = (ArrayList<EndPointResponse>) response.get(key);
			list.forEach(value -> {
				value.setBody("1800Flowers");
				value.setTitle("1800Flowers");
			});
			response.get(key).addAll(list);
		}
		return response;

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
