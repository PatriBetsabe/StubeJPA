package hello.persistence;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.repository.CrudRepository;

import hello.domain.User;
import hello.domain.Video;

//This will be AUTO IMPLEMENTED by Spring into a Bean called userRepository
//CRUD refers Create, Read, Update, Delete

interface HelperVideoRepository extends CrudRepository<Video, Integer> {

	List<Video> findAllByUser(User user);
	
	
    @Transactional
	void removeByUser(User user);
 
}
