package org.jlmartinez.test.controller;

import static org.junit.Assert.assertNotNull;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.webAppContextSetup;

import java.io.IOException;
import java.nio.charset.Charset;
import java.util.Arrays;

import org.jlmartinez.test.JlmartinezTestApplication;
import org.jlmartinez.test.model.Address;
import org.jlmartinez.test.model.User;
import org.jlmartinez.test.repository.UsersRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.mock.http.MockHttpOutputMessage;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.context.WebApplicationContext;

/**
 * @author Josh Long
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = JlmartinezTestApplication.class)
@WebAppConfiguration
public class RestControllerTest2 {


    private MediaType contentType = new MediaType(MediaType.APPLICATION_JSON.getType(),
            MediaType.APPLICATION_JSON.getSubtype(),
            Charset.forName("utf8"));

    private MockMvc mockMvc;
    @SuppressWarnings("rawtypes")
	private HttpMessageConverter mappingJackson2HttpMessageConverter;


    @Autowired
    private WebApplicationContext webApplicationContext;

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

        //this.userRepository.deleteAllInBatch();
 
        /*
        this.account = accountRepository.save(new Account(userName, "password"));
        this.bookmarkList.add(userRepository.save(new Bookmark(account, "http://bookmark.com/1/" + userName, "A description")));
        this.bookmarkList.add(userRepository.save(new Bookmark(account, "http://bookmark.com/2/" + userName, "A description")));
        */
    }

    /*
    @Test
    public void userNotFound() throws Exception {
        mockMvc.perform(post("/george/bookmarks/")
                .content(this.json(new Bookmark(null, null, null)))
                .contentType(contentType))
                .andExpect(status().isNotFound());
    }
	*/
    @Test
    public void createUserPost() throws Exception {
    	
    	Address address = new Address();
    	address.setCity("CS");
    	address.setCountry("ES");
    	address.setState("CV");
    	address.setZip("12001");
    	
    	User user = new User();
    	user.setBirthDate("01-01-1981");
    	user.setEmail("pepe@gmail.com");
    	user.setName("pepe");
    	user.setAddress(address);
    	
    	String msgContent = this.json(user); 
        mockMvc.perform(post("/users")
                .content(msgContent)
                .contentType(contentType))
                .andExpect(status().isCreated());
    }

    /*
    @Test
    public void readSingleBookmark() throws Exception {
        mockMvc.perform(get("/bookmarks/" + userName + "/" + this.bookmarkList.get(0).getId()))
                .andExpect(status().isOk())
                .andExpect(content().contentType(contentType))
                .andExpect(jsonPath("$.id", is(this.bookmarkList.get(0).getId().intValue())))
                .andExpect(jsonPath("$.uri", is("http://bookmark.com/1/" + userName)))
                .andExpect(jsonPath("$.description", is("A description")));
    }
	
    @Test
    public void readBookmarks() throws Exception {
        mockMvc.perform(get("/bookmarks/" + userName))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentType(contentType))
                .andExpect(jsonPath("$", hasSize(2)))
                .andExpect(jsonPath("$[0].id", is(this.bookmarkList.get(0).getId().intValue())))
                .andExpect(jsonPath("$[0].uri", is("http://bookmark.com/1/" + userName)))
                .andExpect(jsonPath("$[0].description", is("A description")))
                .andExpect(jsonPath("$[1].id", is(this.bookmarkList.get(1).getId().intValue())))
                .andExpect(jsonPath("$[1].uri", is("http://bookmark.com/2/" + userName)))
                .andExpect(jsonPath("$[1].description", is("A description")));
    }

    @Test
    public void createBookmark() throws Exception {
        String bookmarkJson = json(new Bookmark(
                this.account, "http://spring.io", "a bookmark to the best resource for Spring news and information"));

        this.mockMvc.perform(post("/bookmarks/" + userName)
                .contentType(contentType)
                .content(bookmarkJson))
                .andExpect(status().isCreated());
    }
	*/
    protected String json(Object o) throws IOException {
        MockHttpOutputMessage mockHttpOutputMessage = new MockHttpOutputMessage();
        this.mappingJackson2HttpMessageConverter.write(
                o, MediaType.APPLICATION_JSON, mockHttpOutputMessage);
        return mockHttpOutputMessage.getBodyAsString();
    }
}
