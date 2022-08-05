package com.deutsche.nace;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.deutsche.nace.model.request.PutRequest;
import com.deutsche.nace.model.response.GetResponse;
import com.deutsche.nace.service.UpdateRecordService;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ExtendWith(SpringExtension.class)
public class NaceControllerIntegrationTest {

	@Autowired
	private TestRestTemplate restTemplate;

	@Autowired
	private UpdateRecordService updateRecordService;

	@Test
	public void getNaceDetailsSuccess() {

		updateRecordService.putNaceDetails(PutRequest.builder().order("398481").level("1").code("A").parent("")
				.description("AGRICULTURE, FORESTRY AND FISHING")
				.thisItemIncludes("This section includes the exploitation of vegetal and animal natural resources.")
				.thisItemAlsoIncludes("").rulings("").thisItemExcludes("").referenceToIsicRev4("A").build());

		ResponseEntity<GetResponse> response = restTemplate.getForEntity("/nace/v1/service/398481", GetResponse.class);

		assertEquals(response.getBody().getReferenceToIsicRev4(), "A");
		assertEquals(response.getBody().getLevel(), "1");
		assertThat(response.getStatusCodeValue()).isEqualTo(200);

	}

	@Test
	public void getNaceDetailsFailure() {

		ResponseEntity<GetResponse> response = restTemplate.getForEntity("/nace/v1/service/12345", GetResponse.class);
		assertThat(response.getStatusCodeValue()).isEqualTo(404);

	}

	@Test
	public void putNaceDetailsSuccess() {

		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);

		HttpEntity<String> request = new HttpEntity<>("{\r\n" + "  \"order\": \"398485\",\r\n"
				+ "  \"level\": \"1\",\r\n" + "  \"code\": \"A\",\r\n" + "  \"parent\": \"\",\r\n"
				+ "  \"description\": \"AGRICULTURE, FORESTRY AND FISHING\",\r\n"
				+ "  \"thisItemIncludes\": \"This section includes the exploitation of vegetal and animal natural resources.\",\r\n"
				+ "  \"thisItemAlsoIncludes\": \"\",\r\n" + "  \"rulings\": \"\",\r\n"
				+ "  \"thisItemExcludes\": \"\",\r\n" + "  \"referenceToIsicRev4\": \"A\"\r\n" + "}", headers);

		ResponseEntity<Void> response = restTemplate.exchange("/nace/v1/service", HttpMethod.PUT, request, Void.class);

		assertThat(response.getStatusCodeValue()).isEqualTo(200);
	}

	@Test
	public void putNaceDetailsFailure() {

		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);

		HttpEntity<String> request = new HttpEntity<>("{\r\n" + "  \"order\": \"\",\r\n" + "  \"level\": \"1\",\r\n"
				+ "  \"code\": \"A\",\r\n" + "  \"parent\": \"\",\r\n"
				+ "  \"description\": \"AGRICULTURE, FORESTRY AND FISHING\",\r\n"
				+ "  \"thisItemIncludes\": \"This section includes the exploitation of vegetal and animal natural resources.\",\r\n"
				+ "  \"thisItemAlsoIncludes\": \"\",\r\n" + "  \"rulings\": \"\",\r\n"
				+ "  \"thisItemExcludes\": \"\",\r\n" + "  \"referenceToIsicRev4\": \"A\"\r\n" + "}", headers);

		ResponseEntity<Void> response = restTemplate.exchange("/nace/v1/service", HttpMethod.PUT, request, Void.class);

		assertThat(response.getStatusCodeValue()).isEqualTo(400);
	}

}
