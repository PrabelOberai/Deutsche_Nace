package com.deutsche.nace.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.deutsche.nace.entity.Nace;
import com.deutsche.nace.exception.NaceBadRequestException;
import com.deutsche.nace.model.request.PutRequest;
import com.deutsche.nace.repository.NaceRepository;

@Service
public class UpdateRecordServiceImpl implements UpdateRecordService {

	@Autowired
	NaceRepository naceRepository;

	@Override
	public Nace putNaceDetails(PutRequest request) {
		// TODO Auto-generated method stub
		if (isNullOrBlank(request.getOrder())) {
			throw new NaceBadRequestException();
		}
		return naceRepository.findById(request.getOrder()).map(itemRecord -> {
			itemRecord.setOrderId(request.getOrder());
			itemRecord.setLevel(request.getLevel());
			itemRecord.setCode(request.getCode());
			itemRecord.setParent(request.getParent());
			itemRecord.setDescription(request.getDescription());
			itemRecord.setThisItemIncludes(request.getThisItemIncludes());
			itemRecord.setThisItemAlsoIncludes(request.getThisItemAlsoIncludes());
			itemRecord.setRulings(request.getRulings());
			itemRecord.setThisItemExcludes(request.getThisItemExcludes());
			itemRecord.setReferenceToIsicRev4(request.getReferenceToIsicRev4());
			return naceRepository.save(itemRecord);
		}).orElseGet(() -> {
			return naceRepository.save(mapNewRecord(request));
		});
	}
	
	private Nace mapNewRecord(PutRequest request) {
		Nace newItemRecord = new Nace();
		newItemRecord.setOrderId(request.getOrder());
		newItemRecord.setLevel(request.getLevel());
		newItemRecord.setCode(request.getCode());
		newItemRecord.setParent(request.getParent());
		newItemRecord.setDescription(request.getDescription());
		newItemRecord.setThisItemIncludes(request.getThisItemIncludes());
		newItemRecord.setThisItemAlsoIncludes(request.getThisItemAlsoIncludes());
		newItemRecord.setRulings(request.getRulings());
		newItemRecord.setThisItemExcludes(request.getThisItemExcludes());
		newItemRecord.setReferenceToIsicRev4(request.getReferenceToIsicRev4());
		return newItemRecord;
	}
	
	private boolean isNullOrBlank(String param) {
		return param == null || param.trim().length() == 0;
	}
	
	@Autowired
	public void setNaceRepository(NaceRepository naceRepository) {
		this.naceRepository = naceRepository;
	}

}
