package hello.persistence;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import hello.domain.User;

// This will be AUTO IMPLEMENTED by Spring into a Bean called userRepository
// CRUD refers Create, Read, Update, Delete

interface HelperUserRepository extends CrudRepository<User, Integer> {
	
	User findByEmail(String email);
	
	List<User> findByUsername(String username);
	
	
	
	
	
	
}
