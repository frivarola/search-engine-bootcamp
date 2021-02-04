package com.desafio.searchEngine;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;


import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class SearchEngineApplicationTests {

	@Autowired
	private MockMvc mockMvc;

	@Test
	void contextLoads() {
	}

	@Test
	void testService() throws Exception{
		this.mockMvc.perform(get("/api/v1/articles"))
				.andDo(print())
				.andExpect(status().isOk());
	}

	@Test
	void testService1() throws Exception{
		this.mockMvc.perform(get("/api/v1/articles?category=Indumentaria"))
				.andDo(print())
				.andExpect(status().isOk());
	}

	@Test
	void testService2() throws Exception{
		this.mockMvc.perform(get("/api/v1/articles?category=Indumentaria&order=3"))
				.andDo(print())
				.andExpect(status().isOk());
	}

	@Test
	void testService3() throws Exception{
		this.mockMvc.perform(get("/api/v1/articles?category=Celulares&order=0"))
				.andDo(print())
				.andExpect(status().isOk());
	}

	@Test
	void testService4() throws Exception{
		this.mockMvc.perform(get("/api/v1/articles?category=Herramientas&freeShip=true"))
				.andDo(print())
				.andExpect(status().isOk());
	}


	@Test
	void testService5() throws Exception{
		this.mockMvc.perform(get("/api/v1/articles?category=Herramientas&brand=Makita"))
				.andDo(print())
				.andExpect(status().isOk());
	}


	@Test
	void testService6() throws Exception{
		this.mockMvc.perform(get("/api/v1/articles?freeShip=true&brand=Makita"))
				.andDo(print())
				.andExpect(status().isOk());
	}

}
