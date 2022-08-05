package com.deutsche.nace.service;

import com.deutsche.nace.entity.Nace;
import com.deutsche.nace.model.request.PutRequest;

public interface UpdateRecordService {

	Nace putNaceDetails(PutRequest request);
	
}
