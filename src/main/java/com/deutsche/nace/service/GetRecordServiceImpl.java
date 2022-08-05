package com.deutsche.nace.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.deutsche.nace.entity.Nace;
import com.deutsche.nace.model.response.GetResponse;
import com.deutsche.nace.repository.NaceRepository;

@Service
public class GetRecordServiceImpl implements GetRecordService {

	@Autowired
	NaceRepository naceRepository;
	
	@Override
	public GetResponse getRecord(String orderId) {
		GetResponse getResponse = new GetResponse();
		Nace nace = naceRepository.getNaceDataFromOrderId(orderId);
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
