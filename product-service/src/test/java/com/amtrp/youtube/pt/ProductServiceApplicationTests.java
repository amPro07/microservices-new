package com.amtrp.youtube.pt;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.testcontainers.containers.MongoDBContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

import com.amtrp.youtube.pt.dto.ProductResponse;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@Testcontainers
@SpringBootTest
@AutoConfigureMockMvc
class ProductServiceApplicationTests {
	
	@Container
	static MongoDBContainer mongoDBContainer = new MongoDBContainer("mongo:4.4.2");
	
	@Autowired
	private ObjectMapper objectMapper;
	
	@Autowired
	private MockMvc mockMvc;
	
	@DynamicPropertySource
	static void setProperties(DynamicPropertyRegistry dynamicPropertyRegistry) {
		dynamicPropertyRegistry.add("spring.data.mongodb.uri", mongoDBContainer::getReplicaSetUrl);
	}

	@Test
	void shouldCreateProduct() throws JsonProcessingException {
		ProductResponse productResponse = getProductResponse();
		String valueAsString = objectMapper.writeValueAsString(productResponse);
		try {
			mockMvc.perform(MockMvcRequestBuilders.post("/api/product")
					.contentType(MediaType.APPLICATION_JSON)
					.content(valueAsString))
			.andExpect(status().isCreated());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	private ProductResponse getProductResponse() {
		return ProductResponse.builder()
				.name("iPad")
				.description("AN Apple tabletproduct")
				.price(28000)
				.build();
		
	}

}
