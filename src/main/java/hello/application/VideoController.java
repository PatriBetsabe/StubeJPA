package hello.application;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import hello.application.dto.VideoDTO;
import hello.domain.User;
import hello.domain.Video;
import hello.persistence.VideoRepository;
import hello.utilities.InvalidParamException;
import hello.utilities.NotFoundException;

@Controller
public class VideoController {
	@Autowired
	private VideoRepository videoRepository;
	@Autowired
	private UserController userController;

	public VideoDTO uploadVideo(int userId, VideoDTO videoToUpload) throws NotFoundException, InvalidParamException {
		User user = userController.getUser(userId);

		Video video = new Video(user, videoToUpload.getTitle(), 
				videoToUpload.getDescription(), videoToUpload.getURL());
		
		videoRepository.save(video);
		
		return new VideoDTO(video);
	}

	public List<VideoDTO> getAllVideos(int userId) throws NotFoundException, InvalidParamException {
		
		
		User user = userController.getUser(userId);
	
		if(user.getId()!=userId)
				throw new InvalidParamException();
		
		List<VideoDTO> videoDTO = new ArrayList<>();
		
		List<Video> videos = videoRepository.getAllVideos(user);
		for(Video v: videos)
			videoDTO.add(new VideoDTO(v));

		
		return videoDTO;
	}

	public VideoDTO getVideo(int userId, int videoId) throws NotFoundException, InvalidParamException {
		User user = userController.getUser(userId);
		Video video = videoRepository.getVideoById(videoId);

		
		if(user!=video.getUser()) {
			throw new InvalidParamException();
		}
		return new VideoDTO(video);
	}

	public void removeVideos(int userId) throws NotFoundException {
		User user = userController.getUser(userId);
		videoRepository.removeVideos(user);

	}

	public void removeUserVideo(int userId, int videoId) throws NotFoundException {
		User user = userController.getUser(userId);
		videoRepository.removeVideo(videoId); 
		
	}

	public VideoDTO updateVideo(int videoId, VideoDTO videoToUpdate) throws InvalidParamException, NotFoundException {
		Video video = videoRepository.getVideoById(videoId);
		video.setDescription(videoToUpdate.getDescription());
		video.setTitle(videoToUpdate.getTitle());
		video.setUrl(videoToUpdate.getURL());
		videoRepository.save(video);
		
		return new VideoDTO(video);
	}

}