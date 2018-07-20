//package org.jlmartinez.test.controller;
//
//import java.util.ArrayList;
//import java.util.List;
//
//import org.junit.Test;
//import org.junit.runner.RunWith;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
//import org.springframework.boot.test.web.client.TestRestTemplate;
//import org.springframework.boot.web.server.LocalServerPort;
//import org.springframework.http.MediaType;
//import org.springframework.http.converter.HttpMessageConverter;
//import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
//import org.springframework.test.context.junit4.SpringRunner;
//
//// https://spring.io/guides/gs/actuator-service/
//
//@RunWith(SpringRunner.class)
//@SpringBootTest(webEnvironment = WebEnvironment.DEFINED_PORT)
//// @TestPropertySource(properties = {"management.port=0"})
//public class RestControllerTest {
//
//	@LocalServerPort
//	private int port;
//
//	/*
//	@Value("${local.management.port}")
//	private int mgt;
//	 */
//	@Autowired
//	private TestRestTemplate testRestTemplate;
//
//	@Test
//	public void shouldReturn200WhenSendingRequestToController() throws Exception {
//		/*
//		List<HttpMessageConverter<?>> messageConverters = new ArrayList<HttpMessageConverter<?>>();        
//		//Add the Jackson Message converter
//		MappingJackson2HttpMessageConverter converter = new MappingJackson2HttpMessageConverter();
//		// Note: here we are making this converter to process any kind of response, 
//		// not only application/*json, which is the default behaviour
//		List<MediaType> mediaTypes = new ArrayList<>();
//	    mediaTypes.add(MediaType.APPLICATION_JSON_UTF8);
//	    mediaTypes.add(MediaType.TEXT_HTML);
//		converter.setSupportedMediaTypes(mediaTypes);         
//		messageConverters.add(converter);  
//		testRestTemplate.setMessageConverters(messageConverters);  
//		*/
//		@SuppressWarnings("rawtypes")
//		/*ResponseEntity<Map> entity = this.testRestTemplate.getForEntity(
//				"http://localhost:" + this.port + "/hello-world", Map.class);
//		 */
//		ResponseEntity<List> entity = this.testRestTemplate.getForEntity(
//				"http://localhost:" + this.port + "/users", List.class);
//
//		then(entity.getStatusCode()).isEqualTo(HttpStatus.OK);
//	}
//
//	/*
//	@Test
//	public void shouldReturn200WhenSendingRequestToManagementEndpoint() throws Exception {
//		@SuppressWarnings("rawtypes")
//		ResponseEntity<Map> entity = this.testRestTemplate.getForEntity(
//				"http://localhost:" + this.mgt + "/actuator/info", Map.class);
//
//		then(entity.getStatusCode()).isEqualTo(HttpStatus.OK);
//	}
//	 */
//}