package com.app.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.app.model.EndPointResponse;

@Service
public class EndPointService {

	public Map<Integer, List<EndPointResponse>> getUniqueId(EndPointResponse[] str) {
		Map<Integer, List<EndPointResponse>> response = null;
		if (str != null) {
			response = Arrays.asList(str).stream().collect(Collectors.groupingBy(EndPointResponse::getUserId));
		}
		return response;

	}

	public Map<Integer, List<EndPointResponse>> update(EndPointResponse[] str) {

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

}
