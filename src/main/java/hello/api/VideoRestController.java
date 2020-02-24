package hello.api;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import hello.application.VideoController;
import hello.application.dto.VideoDTO;
import hello.utilities.InvalidParamException;
import hello.utilities.NotFoundException;

@RestController
@CrossOrigin
public class VideoRestController {
	@Autowired
	private VideoController controller;
	
	protected String toJson(Object o) {
		Gson gson = new GsonBuilder().excludeFieldsWithoutExposeAnnotation().create();
		return gson.toJson(o);
	}

	protected HttpHeaders initHeader() {
		HttpHeaders httpHeaders = new HttpHeaders();
		httpHeaders.setContentType(MediaType.APPLICATION_JSON);
		return httpHeaders;
	}
	
	@PostMapping(value = "/users/{userId}/videos" ,produces = "application/json;charset=UTF-8")
	public ResponseEntity<String> upload(@PathVariable int userId, @RequestBody String jVideo) throws NotFoundException, InvalidParamException {
		
		VideoDTO video = new Gson().fromJson(jVideo, VideoDTO.class);
		VideoDTO result = controller.uploadVideo(userId, video);
		
		return new ResponseEntity<String>(toJson(result), initHeader(), HttpStatus.CREATED);
		
	}
	
	@GetMapping(value = "/users/{userId}/videos", produces = "application/json;charset=UTF-8")
	public ResponseEntity<String> getAllVideos(@PathVariable int userId) throws NotFoundException, InvalidParamException {
				
		List<VideoDTO> videos = controller.getAllVideos(userId);
		
		return new ResponseEntity<String>(toJson(videos), initHeader(), HttpStatus.OK);
	}
	
	@GetMapping(value = "/users/{userId}/videos/{videoId}", produces = "application/json;charset=UTF-8")
	public ResponseEntity<String> getVideo(@PathVariable int userId ,@PathVariable int videoId) throws NotFoundException, InvalidParamException {
		
		VideoDTO video = controller.getVideo(userId, videoId);
		
		return new ResponseEntity<String>(toJson(video), initHeader(), HttpStatus.OK);
		
	}
	
	@DeleteMapping(value = "/users/{userId}/videos", produces = "application/json;charset=UTF-8")
	public ResponseEntity<String> removeVideos(@PathVariable int userId) throws NotFoundException {
		controller.removeVideos(userId);
		return new ResponseEntity<String>(initHeader(), HttpStatus.OK);
		
	}
	
	@DeleteMapping(value = "/users/{userId}/videos/{videoId}", produces = "application/json;charset=UTF-8")
	public ResponseEntity<String> removeUserVideo(@PathVariable int userId, int videoId) throws NotFoundException {
		controller.removeUserVideo(userId, videoId);
		return new ResponseEntity<String>(initHeader(), HttpStatus.OK);
		
	}
	
	@PutMapping(value = "/users/{userId}/videos/{videoId}", produces = "application/json;charset=UTF-8")
	public ResponseEntity<String> updateVideo(@PathVariable int videoId, @RequestBody String json) throws InvalidParamException, NotFoundException{
		
		VideoDTO videoToUpdate = new Gson().fromJson(json, VideoDTO.class);
		
		VideoDTO video = controller.updateVideo(videoId, videoToUpdate);
		
		return new ResponseEntity<String>(toJson(video), initHeader(), HttpStatus.OK);
	
	}
	


}
