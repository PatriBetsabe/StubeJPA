package hello.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import hello.utilities.Encryptor;
import hello.utilities.InvalidParamException;

@Entity(name="user")
public class User {
    @Id
    @GeneratedValue(strategy=GenerationType.SEQUENCE)
    private Integer id;
    private String username;
    private String email;
    private String password;

    public User() {
    	
    }
    
  	public User(String email, String password, String username) throws InvalidParamException{
		if (!email.contains("@"))
			throw new InvalidParamException();
		this.username=username;
		this.email=email;
		this.password= Encryptor.encryptPassword(password);
		
	}

	public Integer getId() {
		return id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username){
		if(!username.equals(""))
			this.username = username;
	}

	public String getEmail() {
		return email;
	}
	
	public void setEmail(String email) throws InvalidParamException{
		if(!email.equals(""))
			this.email = email;
		if (!email.contains("@"))
			throw new InvalidParamException();
	}

	public String getPassword() {
		return password;
	}
	
	public void setPassword(String password) {
		this.password = password;
	}
	
	public void checkPasswordCorrect(String password) throws InvalidParamException {
		if(!this.password.equals(password) && password!=null && password.length()<7) throw new InvalidParamException();
	}
	
	public void checkPasswordIsCorrect(String password) throws InvalidParamException {
		Encryptor.checkIfPasswordMatches(password, this.password);
	}

	public void checkEmailCorrect(String email) throws InvalidParamException {
		if(!this.email.equals(email)) throw new InvalidParamException();
	}

}

