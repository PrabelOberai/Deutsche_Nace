package com.deutsche.nace.service;

import com.deutsche.nace.model.response.GetResponse;

public interface GetRecordService {

	GetResponse getNaceDetails(String orderId);
	
}
