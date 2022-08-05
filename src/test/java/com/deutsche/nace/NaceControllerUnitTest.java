package com.deutsche.nace;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.deutsche.nace.entity.Nace;
import com.deutsche.nace.exception.NaceDetailsNotFoundException;
import com.deutsche.nace.model.response.GetResponse;
import com.deutsche.nace.service.GetRecordService;
import com.deutsche.nace.service.UpdateRecordService;

@WebMvcTest
@ExtendWith(SpringExtension.class)
public class NaceControllerUnitTest {

	@Autowired
	MockMvc mockmvc;

	@MockBean
	private GetRecordService getRecordService;

	@MockBean
	private UpdateRecordService updateRecordService;

	@Test
	public void getNaceDetailsSuccess() throws Exception {

		given(getRecordService.getNaceDetails(any(String.class))).willReturn(GetResponse.builder().order("398481")
				.level("1").code("A").parent("").description("AGRICULTURE, FORESTRY AND FISHING")
				.thisItemIncludes(
						"This section includes the exploitation of vegetal and animal natural resources, comprising the activities of growing of crops, raising and breeding of animals, harvesting of timber and other plants, animals or animal products from a farm or their natural habitats.")
				.thisItemAlsoIncludes("").rulings("").thisItemExcludes("").referenceToIsicRev4("A").build());

		mockmvc.perform(MockMvcRequestBuilders.get("/nace/v1/service/Test")).andExpect(status().isOk());

	}

	@Test
	public void getNaceDetailsFailure() throws Exception {

		given(getRecordService.getNaceDetails(any(String.class))).willThrow(new NaceDetailsNotFoundException());

		mockmvc.perform(MockMvcRequestBuilders.get("/nace/v1/service/Test")).andExpect(status().isNotFound());

	}

	@Test
	public void putNaceDetailsSuccess() throws Exception {

		given(updateRecordService.putNaceDetails(any())).willReturn(Nace.builder().orderId("398481").level("1")
				.code("A").parent("").description("AGRICULTURE, FORESTRY AND FISHING")
				.thisItemIncludes(
						"This section includes the exploitation of vegetal and animal natural resources, comprising the activities of growing of crops, raising and breeding of animals, harvesting of timber and other plants, animals or animal products from a farm or their natural habitats.")
				.thisItemAlsoIncludes("").rulings("").thisItemExcludes("").referenceToIsicRev4("A").build());

		mockmvc.perform(MockMvcRequestBuilders.put("/nace/v1/service").contentType(MediaType.APPLICATION_JSON)
				.content("{\r\n" + "  \"order\": \"398485\",\r\n" + "  \"level\": \"1\",\r\n" + "  \"code\": \"A\",\r\n"
						+ "  \"parent\": \"\",\r\n" + "  \"description\": \"AGRICULTURE, FORESTRY AND FISHING\",\r\n"
						+ "  \"thisItemIncludes\": \"This section includes the exploitation of vegetal and animal natural resources, comprising the activities of growing of crops, raising and breeding of animals, harvesting of timber and other plants, animals or animal products from a farm or their natural habitats.\",\r\n"
						+ "  \"thisItemAlsoIncludes\": \"\",\r\n" + "  \"rulings\": \"\",\r\n"
						+ "  \"thisItemExcludes\": \"\",\r\n" + "  \"referenceToIsicRev4\": \"A\"\r\n" + "}"))
				.andExpect(status().isOk());

	}

	@Test
	public void putNaceDetailsFailure() throws Exception {

		given(updateRecordService.putNaceDetails(any())).willReturn(Nace.builder().level("1").code("A").parent("")
				.description("AGRICULTURE, FORESTRY AND FISHING")
				.thisItemIncludes(
						"This section includes the exploitation of vegetal and animal natural resources, comprising the activities of growing of crops, raising and breeding of animals, harvesting of timber and other plants, animals or animal products from a farm or their natural habitats.")
				.thisItemAlsoIncludes("").rulings("").thisItemExcludes("").referenceToIsicRev4("A").build());

		mockmvc.perform(MockMvcRequestBuilders.put("/nace/v1/service").contentType(MediaType.APPLICATION_JSON)
				.content("{\r\n" + "  \"level\": \"1\",\r\n" + "  \"code\": \"A\",\r\n" + "  \"parent\": \"\",\r\n"
						+ "  \"description\": \"AGRICULTURE, FORESTRY AND FISHING\",\r\n"
						+ "  \"thisItemIncludes\": \"This section includes the exploitation of vegetal and animal natural resources, comprising the activities of growing of crops, raising and breeding of animals, harvesting of timber and other plants, animals or animal products from a farm or their natural habitats.\",\r\n"
						+ "  \"thisItemAlsoIncludes\": \"\",\r\n" + "  \"rulings\": \"\",\r\n"
						+ "  \"thisItemExcludes\": \"\",\r\n" + "  \"referenceToIsicRev4\": \"A\"\r\n" + "}"))
				.andExpect(status().isBadRequest());

	}

}
