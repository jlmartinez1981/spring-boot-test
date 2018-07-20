package org.jlmartinez.test.repository;

import org.jlmartinez.test.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsersRepository extends JpaRepository<User, Integer> {

	// TODO jlmartinez implement needed methods out of standard crud operations if any
}
