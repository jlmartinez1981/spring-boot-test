package org.jlmartinez.test.controller;

import java.net.URI;
import java.util.List;

import javax.validation.Valid;

import org.jlmartinez.test.model.User;
import org.jlmartinez.test.repository.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

// https://www.callicoder.com/spring-boot-rest-api-tutorial-with-mysql-jpa-hibernate/
// https://www.callicoder.com/spring-boot-jpa-hibernate-postgresql-restful-crud-api-example/
// https://github.com/callicoder/spring-boot-mysql-rest-api-tutorial
// https://www.concretepage.com/spring-boot/spring-boot-rest-example
@RestController
public class UserController {
	
	@Autowired
	private UsersRepository usersRepository;

	/*
	@GetMapping("/users")
	public Page<User> getUsers(Pageable pageable) {
		/*
	 * List<User> users = usersRepository.findAll();
		users.forEach(u -> System.out.println("AddressID: " + u.getAddress().getId()));
	 *//*
		return usersRepository.findAll(pageable);
	}*/

	@GetMapping("/users")
	public ResponseEntity<List<User>> getUsers() {

		List<User> users = usersRepository.findAll();
		//users.forEach(u -> System.out.println("AddressID: " + u.getAddress().getId()));

		return new ResponseEntity<List<User>>(users, HttpStatus.OK);
	}

	@PostMapping("/users")
	public ResponseEntity<User> createUsers(@Valid @RequestBody User user, BindingResult bindingResult) {
		
		if (bindingResult.hasErrors()){
	        return ResponseEntity.status(HttpStatus.METHOD_NOT_ALLOWED).build();
	    }

		User result = usersRepository.save(user);
		/*URI location = ServletUriComponentsBuilder
				.fromCurrentRequest()
				.path("/{id}")
				.buildAndExpand(result.getId())
				.toUri();
		*/
		return new ResponseEntity<User>(result, HttpStatus.CREATED);
	}

	/*
	@PutMapping("/users/{userId}")
	public User updateQuestion(@PathVariable int userId,
			@Valid @RequestBody User userRequest) {
		return usersRepository.findById(userId)
				.map(user -> {
					user.setTitle(userRequest.getTitle());
					user.setDescription(userRequest.getDescription());
					return userRepository.save(user);
				}).orElseThrow(() -> new ResourceNotFoundException("Question not found with id " + questionId));
	}

	@DeleteMapping("/questions/{questionId}")
	public ResponseEntity<?> deleteQuestion(@PathVariable Long questionId) {
		return usersRepository.findById(questionId)
				.map(question -> {
					questionRepository.delete(question);
					return ResponseEntity.ok().build();
				}).orElseThrow(() -> new ResourceNotFoundException("Question not found with id " + questionId));
	}
	 */
}
