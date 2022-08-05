package com.deutsche.nace.model.request;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PutRequest {

	@Valid
	@NotNull(message = "'orderId' is required in the request body")
	private String order;

	private String level;

	private String code;

	private String parent;

	private String description;

	private String thisItemIncludes;

	private String thisItemAlsoIncludes;

	private String rulings;

	private String thisItemExcludes;

	private String referenceToIsicRev4;

}
