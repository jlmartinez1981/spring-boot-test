package org.jlmartinez.test.controller;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertNotNull;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.webAppContextSetup;

import java.io.IOException;
import java.nio.charset.Charset;
import java.time.LocalDateTime;
import java.util.Arrays;

import org.jlmartinez.test.JlmartinezTestApplication;
import org.jlmartinez.test.model.Address;
import org.jlmartinez.test.model.User;
import org.jlmartinez.test.repository.UsersRepository;
import org.jlmartinez.test.utils.TestUtils;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.mock.http.MockHttpOutputMessage;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.context.WebApplicationContext;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = JlmartinezTestApplication.class)
@Sql({"/test-data.sql"})
@WebAppConfiguration
public class RestControllerTest {


	private MediaType contentType = new MediaType(MediaType.APPLICATION_JSON.getType(),
			MediaType.APPLICATION_JSON.getSubtype(),
			Charset.forName("utf8"));

	private MockMvc mockMvc;
	@SuppressWarnings("rawtypes")
	private HttpMessageConverter mappingJackson2HttpMessageConverter;

	@Autowired
	private WebApplicationContext webApplicationContext;

	@Autowired
	private UsersRepository userRepository;

	@Autowired
	void setConverters(HttpMessageConverter<?>[] converters) {

		this.mappingJackson2HttpMessageConverter = Arrays.asList(converters).stream()
				.filter(hmc -> hmc instanceof MappingJackson2HttpMessageConverter)
				.findAny()
				.orElse(null);

		assertNotNull("the JSON message converter must not be null",
				this.mappingJackson2HttpMessageConverter);
	}

	@Before
	public void setup() throws Exception {
		this.mockMvc = webAppContextSetup(webApplicationContext).build();
		this.userRepository.deleteAllInBatch();
	}

	@Test
	public void userNotFound() throws Exception {
		mockMvc.perform(get("/users/0")
				.contentType(contentType))
		.andExpect(status().isNotFound());
	}

	@Test
	public void invalidUserId() throws Exception {

		mockMvc.perform(get("/users/-1")
				.contentType(contentType))
		.andExpect(status().is(400));
	}

	@Test
	public void getUsers() throws Exception {

		Address address = TestUtils.createMockAddress("CS", "ES", "CV",
				"Rey D.", "12001");

		User user = TestUtils.createMockUser("isabel", "isabel@gmail.com",
				LocalDateTime.now(), address);

		String msgContent = this.json(user); 
		mockMvc.perform(post("/users")
				.content(msgContent)
				.contentType(contentType))
		.andExpect(status().isCreated());

		address = TestUtils.createMockAddress("CS", "ES", "CV",
				"Street2", "12002");

		user = TestUtils.createMockUser("elena", "elena@gmail.com",
				LocalDateTime.now(), address);

		msgContent = this.json(user); 
		mockMvc.perform(post("/users")
				.content(msgContent)
				.contentType(contentType))
		.andExpect(status().isCreated());

		mockMvc.perform(get("/users"))
		.andDo(print())
		.andExpect(status().isOk())
		.andExpect(content().contentType(contentType))
		.andExpect(jsonPath("$", hasSize(2)));
	}

	@Test
	public void createUserPost() throws Exception {

		Address address = TestUtils.createMockAddress("CS", "ES", "CV",
				"Rey D.", "12001");

		User user = TestUtils.createMockUser("isabel", "isabel@gmail.com",
				LocalDateTime.now(), address);

		String msgContent = this.json(user); 
		mockMvc.perform(post("/users")
				.content(msgContent)
				.contentType(contentType))
		.andExpect(status().isCreated());

		// User without birth date should fail validation
		user = TestUtils.createMockUser("isabel", "isabel@gmail.com",
				null, address);

		msgContent = this.json(user); 
		mockMvc.perform(post("/users")
				.content(msgContent)
				.contentType(contentType))
		.andExpect(status().is4xxClientError());

		//Address without city should fail when committing
		address = TestUtils.createMockAddress(null, "ES", "CV",
				"Rey D.", "12001");
		user = TestUtils.createMockUser("isabel", "isabel@gmail.com",
				LocalDateTime.now(), address);

		msgContent = this.json(user); 
		mockMvc.perform(post("/users")
				.content(msgContent)
				.contentType(contentType))
		.andExpect(status().isInternalServerError());
	}

	private String json(Object o) throws IOException {
		MockHttpOutputMessage mockHttpOutputMessage = new MockHttpOutputMessage();
		this.mappingJackson2HttpMessageConverter.write(
				o, MediaType.APPLICATION_JSON, mockHttpOutputMessage);
		return mockHttpOutputMessage.getBodyAsString();
	}
}
