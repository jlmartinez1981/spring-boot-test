package org.jlmartinez.test.controller;

import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.annotation.PostConstruct;
import javax.validation.Valid;

import org.jlmartinez.test.controller.response.AddressTO;
import org.jlmartinez.test.controller.response.UserTO;
import org.jlmartinez.test.model.User;
import org.jlmartinez.test.repository.UsersRepository;
import org.jlmartinez.test.utils.DateUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Api(value="jlmartinez CloudAppi")
@RestController
public class UserController {
	
	private DateTimeFormatter formatter;
	
	@Value("${date.formatter.pattern}")
	private String formatterPattern;
	
	@Autowired
	private UsersRepository userRepository;

	@PostConstruct
	private void init() {
		formatter = DateTimeFormatter.ofPattern(formatterPattern);
	}
	
	@ApiOperation(value = "List of users")
	@GetMapping(path = "/users", produces= {MediaType.APPLICATION_JSON_UTF8_VALUE})
	public ResponseEntity<List<UserTO>> getUsers() {

		List<UserTO> userResult = new ArrayList<UserTO>();
		List<User> users = userRepository.findAll();
		users.forEach((u) -> {
			UserTO uResp = this.createUserResponse(u);
			userResult.add(uResp);
		});
		return new ResponseEntity<List<UserTO>>(userResult, HttpStatus.OK);
	}

	@ApiOperation(value = "Create user")
	@PostMapping(path = "/users", produces= {MediaType.APPLICATION_JSON_UTF8_VALUE},
	consumes = {MediaType.APPLICATION_JSON_UTF8_VALUE})
	public ResponseEntity<User> createUsers(@Valid @RequestBody User user, BindingResult bindingResult) {

		if (bindingResult.hasErrors()){
			bindingResult.getAllErrors().forEach(error -> {
				System.out.println("Binding Error: " + error.getDefaultMessage());
			});
			return ResponseEntity.status(HttpStatus.METHOD_NOT_ALLOWED).build();
		}

		try {
			User result = userRepository.save(user);
			return new ResponseEntity<User>(result, HttpStatus.CREATED);

		}catch(Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
	}
	
	@ApiOperation(value = "Get one user", response = UserTO.class)
	@GetMapping(path = "/users/{userId}", produces= {MediaType.APPLICATION_JSON_UTF8_VALUE})
	public ResponseEntity<Object> getUser(@PathVariable int userId) {
		if(!this.isValidUserId(userId)) {
			return ResponseEntity.status(400).build();
		}
		try {
			return userRepository.findById(userId)
					.map(user -> {
						UserTO uResp = this.createUserResponse(user);
						return new ResponseEntity<Object>(uResp, HttpStatus.OK);
					}).orElse(ResponseEntity.status(HttpStatus.NOT_FOUND).build());
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
	}

	@PutMapping(path = "/users/{userId}", produces= {MediaType.APPLICATION_JSON_UTF8_VALUE},
			consumes= {MediaType.APPLICATION_JSON_UTF8_VALUE})
	public ResponseEntity<UserTO> updateUser(@PathVariable int userId,
			@Valid @RequestBody User userRequest) {

		if(!this.isValidUserId(userId)) {
			return ResponseEntity.status(400).build();
		}

		Optional<User> searchResult = userRepository.findById(userId);
		if(searchResult.isPresent()) {
			User user = searchResult.get();
			user.setAddress(userRequest.getAddress());
			user.setBirthDate(userRequest.getBirthDate());
			user.setEmail(userRequest.getEmail());
			user.setName(userRequest.getName());

			User result = userRepository.save(user);
			UserTO uResp = this.createUserResponse(result);
			return new ResponseEntity<UserTO>(uResp, HttpStatus.OK);
		}else {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
		}
	}

	@DeleteMapping(path = "/users/{userId}", produces= {MediaType.APPLICATION_JSON_UTF8_VALUE})
	public ResponseEntity<Object> deleteUser(@PathVariable int userId) {
		if(!this.isValidUserId(userId)) {
			return ResponseEntity.status(400).build();
		}

		try {
			return userRepository.findById(userId)
					.map(user -> {
						userRepository.delete(user);
						return ResponseEntity.ok().build();
					}).orElse(ResponseEntity.status(HttpStatus.NOT_FOUND).build());
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
	}

	private boolean isValidUserId(int userId) {
		return userId >= 0;
	}

	private UserTO createUserResponse(User u) {
		AddressTO addrResp = new AddressTO();
		BeanUtils.copyProperties(u.getAddress(), addrResp);

		UserTO uResp = new UserTO();
		BeanUtils.copyProperties(u, uResp);
		uResp.setAddress(addrResp);
		uResp.setBirthDate(DateUtils.localDateTimeToString(u.getBirthDate(), formatter));
		return uResp;
	}
}
