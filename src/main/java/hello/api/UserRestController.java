package hello.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import hello.application.UserController;
import hello.application.dto.UserDTO;
import hello.utilities.InvalidParamException;
import hello.utilities.NotFoundException;


@RestController
@CrossOrigin
public class UserRestController {

	@Autowired
	private UserController controller;
	
	protected String toJson(Object o) {
		Gson gson = new GsonBuilder().excludeFieldsWithoutExposeAnnotation().create();
		return gson.toJson(o);
	}

	protected HttpHeaders initHeader() {
		HttpHeaders httpHeaders = new HttpHeaders();
		httpHeaders.setContentType(MediaType.APPLICATION_JSON);
		return httpHeaders;
	}
	
	@PostMapping(value = "/users", produces = "application/json;charset=UTF-8")
	public ResponseEntity<String> registerUser(@RequestBody String json) throws InvalidParamException {
		
		UserDTO user = new Gson().fromJson(json, UserDTO.class);
		UserDTO result = controller.register(user);
		
		return new ResponseEntity<String>(toJson(result),initHeader(), HttpStatus.CREATED);
	}
	
	@PutMapping(value = "/users/{userId}", produces = "application/json;charset=UTF-8")
	public ResponseEntity<String> updateUser(@PathVariable int userId, @RequestBody String json) throws InvalidParamException, NotFoundException {
		
		UserDTO userToUpdate = new Gson().fromJson(json, UserDTO.class);
		
		UserDTO user = controller.updateUser(userId, userToUpdate);
		
		return new ResponseEntity<String>(toJson(user),initHeader(), HttpStatus.CREATED);
	}
	
	@GetMapping(value = "/users/{userId}", produces = "application/json;charset=UTF-8")
	public ResponseEntity<String> getUser(@PathVariable int userId) throws InvalidParamException, NotFoundException {
		UserDTO user = controller.getUserDTO(userId);
		return new ResponseEntity<String>(toJson(user), initHeader(), HttpStatus.OK);
	}

	@PostMapping(value = "/login", produces = "application/json;charset=UTF-8")
	public ResponseEntity<String> loginUser(@RequestBody String json) throws InvalidParamException{
		UserDTO userToLogin = new Gson().fromJson(json, UserDTO.class);
		UserDTO user = controller.login(userToLogin);
		return new ResponseEntity<String>(toJson(user), initHeader(), HttpStatus.OK);

	}

}
