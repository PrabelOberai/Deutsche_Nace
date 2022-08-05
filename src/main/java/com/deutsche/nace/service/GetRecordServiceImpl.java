package com.deutsche.nace.service;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.deutsche.nace.controller.NaceController;
import com.deutsche.nace.entity.Nace;
import com.deutsche.nace.exception.NaceDetailsNotFoundException;
import com.deutsche.nace.model.response.GetResponse;
import com.deutsche.nace.repository.NaceRepository;

@Service
public class GetRecordServiceImpl implements GetRecordService {
	
	Logger logger = LogManager.getLogger(NaceController.class);

	@Autowired
	NaceRepository naceRepository;

	@Override
	public GetResponse getNaceDetails(String orderId) {
		GetResponse getResponse = new GetResponse();
		Nace nace = naceRepository.getNaceDataFromOrderId(orderId);
		// Handle Null Pointer on Nace
		if (nace == null) {
			logger.info("Nace details not found for orderId: " + orderId);
			throw new NaceDetailsNotFoundException();
		} else {
			getResponse.setOrder(nace.getOrderId());
			getResponse.setLevel(nace.getLevel());
			getResponse.setCode(nace.getCode());
			getResponse.setParent(nace.getParent());
			getResponse.setDescription(nace.getDescription());
			getResponse.setThisItemIncludes(nace.getThisItemIncludes());
			getResponse.setThisItemAlsoIncludes(nace.getThisItemAlsoIncludes());
			getResponse.setRulings(nace.getRulings());
			getResponse.setThisItemExcludes(nace.getThisItemExcludes());
			getResponse.setReferenceToIsicRev4(nace.getReferenceToIsicRev4());
			return getResponse;
		}
	}

}
