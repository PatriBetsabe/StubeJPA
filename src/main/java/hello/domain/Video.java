package hello.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import hello.utilities.InvalidParamException;

@Entity(name="video")
public class Video {

	@Id
    @GeneratedValue(strategy=GenerationType.SEQUENCE)    
	private Integer id;
	private String title, description, url;
	
	@ManyToOne(targetEntity=User.class)
	@JoinColumn(name="user_id")
	private User user;

	public Video(User user, String title, String description, String url) throws InvalidParamException {
		if (title.equals("") || description.equals(""))
			throw new InvalidParamException();
		if (!url.contains(".com"))
			throw new InvalidParamException();
		
		if(user==null)throw new InvalidParamException();
		
		this.user=user;
		this.title = title;
		this.description = description;
		this.url = url;
	
	}
	
	public Video() {
		
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public Integer getId() {
		return id;
	}

	public User getUser() {
		return this.user;
	}
	
	public String getTitle() {
		return title;
	}

	public String getDescription() {
		return description;
	}

	public String getUrl() {
		return url;
	}
	
	
}

