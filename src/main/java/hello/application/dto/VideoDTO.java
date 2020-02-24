package hello.application.dto;

import hello.domain.Video;
import hello.utilities.InvalidParamException;

public class VideoDTO {

	private String description,url,title;
	private int id;
	
	public VideoDTO(Video video) throws InvalidParamException {
		if(video==null) throw new InvalidParamException();
		description=video.getDescription();
		id=video.getId();
		url=video.getUrl();
		title=video.getTitle();
	}

	public String getDescription() {
		return description;
	}

	public String getURL() {
		return url;
	}

	public String getTitle() {
		return title;
	}

	public int getId() {
		return id;
	}
	
	
	
}
