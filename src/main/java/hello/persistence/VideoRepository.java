package hello.persistence;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import hello.domain.User;
import hello.domain.Video;
import hello.utilities.InvalidParamException;
import hello.utilities.NotFoundException;

@Repository
public class VideoRepository {

	@Autowired
	private HelperVideoRepository repository;

	public void save(Video video) throws InvalidParamException {
		if (video == null)
			throw new InvalidParamException();

		try {
			repository.save(video);
		} catch (Exception e) {
			e.printStackTrace();
			throw new InvalidParamException("Video repetit");
		}
	}
	
	public List<Video> getAllVideos(User user){
		List<Video> result = new ArrayList<>();
		
		for(Video v : repository.findAllByUser(user)) {
			result.add(v);
		}
		return result;
	}
	
	public Video getVideoById(int videoId) throws NotFoundException {
		try {
			return repository.findById(videoId).get();
		}catch(Exception e) {
			throw new NotFoundException();
		}
	}
	
	public void removeVideos(User user) {
		repository.removeByUser(user);
		
	}
	
	public void removeVideo(int videoId) {
		repository.deleteById(videoId);
	}
	
	
	
}

