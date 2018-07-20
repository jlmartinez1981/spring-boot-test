package org.jlmartinez.test.controller;

import javax.validation.Valid;

import org.jlmartinez.test.model.User;
import org.jlmartinez.test.repository.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

// https://www.callicoder.com/spring-boot-rest-api-tutorial-with-mysql-jpa-hibernate/
// https://www.callicoder.com/spring-boot-jpa-hibernate-postgresql-restful-crud-api-example/
// https://github.com/callicoder/spring-boot-mysql-rest-api-tutorial
// https://www.concretepage.com/spring-boot/spring-boot-rest-example
@RestController
public class UserController {

	@Autowired
	private UsersRepository usersRepository;

	@GetMapping("/users")
	public Page<User> getUsers(Pageable pageable) {
		/*
		 * List<User> users = usersRepository.findAll();
		users.forEach(u -> System.out.println("AddressID: " + u.getAddress().getId()));
		 */
		return usersRepository.findAll(pageable);
	}


	@PostMapping("/users")
	public User createUsers(@Valid @RequestBody User user) {
		return usersRepository.save(user);
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
