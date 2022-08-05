package com.deutsche.nace;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

//import org.junit.Test;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.deutsche.nace.entity.Nace;
import com.deutsche.nace.repository.NaceRepository;
import com.fasterxml.jackson.databind.ObjectMapper;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
@WebAppConfiguration
@AutoConfigureMockMvc
public class NaceControllerTest {

	@Autowired
	private MockMvc mockMvc;

	@Autowired
	private WebApplicationContext webApplicationContext;

	@Autowired
	ObjectMapper mapper;

	@MockBean
	private NaceRepository naceRepository;

	Nace nace = new Nace("Test", "Test", "Test", "Test", "Test", "Test", "Test", "Test", "Test", "Test");

	void setUp() {
		mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
	}

	@Test
	public void getRecordSuccess() throws Exception {
		Mockito.when(naceRepository.getNaceDataFromOrderId("Test")).thenReturn(nace);
		mockMvc.perform(MockMvcRequestBuilders.get("/nace/v1/service/Test").contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk()).andExpect(jsonPath("$.parent", is("Test")));
	}

	@Test
	public void updateRecordSuccess() throws Exception {
		Mockito.when(naceRepository.getNaceDataFromOrderId("Test")).thenReturn(nace);
		MockHttpServletRequestBuilder mockRequest = MockMvcRequestBuilders.put("/nace/v1/service")
	            .contentType(MediaType.APPLICATION_JSON)
	            .accept(MediaType.APPLICATION_JSON)
	            .content(this.mapper.writeValueAsString(nace));
		MvcResult result = mockMvc.perform(mockRequest).andReturn();
		assertEquals(result.getResponse().getStatus(),200);
	}
	
	@Test
	public void updateRecordFailure() throws Exception {
		Nace nace = new Nace(null, "Test", "Test", "Test", "Test", "Test", "Test", "Test", "Test", "Test");
		Mockito.when(naceRepository.getNaceDataFromOrderId("Test")).thenReturn(nace);
		MockHttpServletRequestBuilder mockRequest = MockMvcRequestBuilders.put("/nace/v1/service")
	            .contentType(MediaType.APPLICATION_JSON)
	            .accept(MediaType.APPLICATION_JSON)
	            .content(this.mapper.writeValueAsString(nace));
		MvcResult result = mockMvc.perform(mockRequest).andReturn();
		assertEquals(result.getResponse().getStatus(),400);
	}

}
