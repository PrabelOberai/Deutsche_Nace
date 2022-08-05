package com.deutsche.nace.model.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class GetResponse {

	public String order;

	public String level;

	public String code;

	public String parent;

	public String description;

	public String thisItemIncludes;

	public String thisItemAlsoIncludes;

	public String rulings;

	public String thisItemExcludes;

	public String referenceToIsicRev4;
	
}
