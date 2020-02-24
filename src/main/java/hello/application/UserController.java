package hello.application;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import hello.application.dto.UserDTO;
import hello.domain.User;
import hello.persistence.UserRepository;
import hello.utilities.InvalidParamException;
import hello.utilities.NotFoundException;

@Controller
public class UserController {
	@Autowired
	private UserRepository userRepository;
	
	
	public UserDTO register(UserDTO userToCreate) throws InvalidParamException{
		
		User user = new User(userToCreate.getEmail(), userToCreate.getPassword(),userToCreate.getUsername());
		user.checkPasswordCorrect(userToCreate.getPassword());
		userRepository.save(user);
		
		return new UserDTO(user);
	}

	User getUser(int userId) throws NotFoundException {
		return userRepository.getUserById(userId);
	}

	public UserDTO updateUser(int userId, UserDTO userToUpdate) throws InvalidParamException, NotFoundException{
		
		User user = userRepository.getUserById(userId);
		user.setUsername(userToUpdate.getUsername());
		user.setEmail(userToUpdate.getEmail());
		userRepository.save(user);
		return new UserDTO(user);
	}

	public UserDTO getUserDTO(int userId) throws InvalidParamException, NotFoundException {
		User user = userRepository.getUserById(userId);
		return new UserDTO(user);
	}

	public UserDTO login(UserDTO userToLogin) throws InvalidParamException {
		User user = userRepository.getUserByEmail(userToLogin.getEmail());
		user.checkEmailCorrect(userToLogin.getEmail());
		user.checkPasswordCorrect(userToLogin.getPassword());
		return new UserDTO(user);
	}
	
}

