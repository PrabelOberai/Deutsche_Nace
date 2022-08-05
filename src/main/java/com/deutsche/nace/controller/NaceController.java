package com.deutsche.nace.controller;

import javax.validation.Valid;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.deutsche.nace.exception.NaceBadRequestException;
import com.deutsche.nace.exception.NaceDetailsNotFoundException;
import com.deutsche.nace.model.request.PutRequest;
import com.deutsche.nace.model.response.GetResponse;
import com.deutsche.nace.service.GetRecordService;
import com.deutsche.nace.service.UpdateRecordService;

@RestController
@RequestMapping(value = "/nace/v1", produces = MediaType.APPLICATION_JSON_VALUE)
public class NaceController {
	
	Logger logger = LogManager.getLogger(NaceController.class);

	@Autowired
	UpdateRecordService updateRecordService;
	
	@Autowired
	GetRecordService getRecordService;

	@PutMapping("/service")
	public ResponseEntity putNaceDetails(@Valid @RequestBody PutRequest request) throws NaceBadRequestException {
		logger.info("Received record to insert: " + request.toString());
		updateRecordService.putNaceDetails(request);
		return new ResponseEntity<Void>(HttpStatus.OK);
	}
	
	@GetMapping("/service/{orderId}")
	public ResponseEntity<GetResponse> getNaceDetails(@PathVariable String orderId) throws NaceDetailsNotFoundException {
		logger.info("Fetching record for Order Id: " + orderId);
		GetResponse getResponse =  getRecordService.getNaceDetails(orderId);
		return new ResponseEntity<>(getResponse, HttpStatus.OK);
	}

}
