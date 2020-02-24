package hello.application.dto;

import hello.domain.User;
import hello.utilities.InvalidParamException;

public class UserDTO {
	
	private String username,email,password;
	private int id;
	
	public UserDTO(User user) throws InvalidParamException {
		if(user==null) throw new InvalidParamException();
		username=user.getUsername();
		email=user.getEmail();
		password=user.getPassword();
		id=user.getId();
	}

	public String getUsername() {
		if(this.username==null) return "";
		return username;
	}

	public String getEmail() {
		if(this.email==null) return "";
		return email;
	}

	public String getPassword() {
		if(this.password==null) return "";
		return password;
	}

	public int getId() {
		return id;
	}

}
