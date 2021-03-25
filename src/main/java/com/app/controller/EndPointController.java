package com.app.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.app.external.service.EndPointRestService;
import com.app.model.EndPointResponse;

@Controller
public class EndPointController {

	@Autowired
	private EndPointRestService endPointService;

	public long countEndPoints() {
		return endPointService.countEndPoints();
	}

	public Map<Integer, List<EndPointResponse>> getUniqueId() {
		return endPointService.getUniqueId();
	}

	public Map<Integer, List<EndPointResponse>> update() {
		return endPointService.update();
	}

}
