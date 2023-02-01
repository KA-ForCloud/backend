package ForCloud.backend;

import ForCloud.backend.dto.PostDto;
import ForCloud.backend.dto.UserDto;
import ForCloud.backend.repository.UserRepository;
import ForCloud.backend.service.DtoService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.transaction.Transactional;

@SpringBootTest
class BackendApplicationTests {

	@Autowired
	UserRepository userRepository;

	@Autowired
	DtoService dtoService;

	@Test
	@DisplayName("유저 정보 열람")
	void getUserInfoTest(){
		dtoService.getUserInfoByUserId(1L);
	}

	@Test
	@DisplayName("틀린 정보로 유저 정보 열람")
	void getWrongUserInfoTest(){
		dtoService.getUserInfoByUserId(0L);
	}

	@Test
	@DisplayName("게시글 정보 열람")
	void getPostInfoTest() {dtoService.getPostByPostId(1L); }

	@Test
	@DisplayName("틀린 정보로 게시글 정보 열람")
	void getWrongPostInfoTest() {dtoService.getPostByPostId(0L); }


	@Test
	@DisplayName("게시글 정보 저장")
	@Transactional
	void savePostTest() throws JsonProcessingException {
		ObjectMapper objectMapper = new ObjectMapper();
		String json = "{\"status\":1,\"start_time\":\"2023-02-14 13:25:00\",\"end_time\":\"2023-03-14 13:25:00\",\"post_name\":\"dddd\",\"contents\":\"dddd\",\"views\":0,\"durations\":\"2\",\"postCategoryDto\":{\"id\":null,\"spring\":0,\"react\":1,\"python\":1,\"java\":0,\"javascript\":1,\"springboot\":1,\"post_id\":null}}";
		PostDto postDto = objectMapper.readValue(json, PostDto.class);
		dtoService.savePost(postDto,1L);
	}

	@Test
	@DisplayName("틀린 정보로 게시글 정보 저장")
	@Transactional
	void saveWrongPostTest() throws JsonProcessingException {
		ObjectMapper objectMapper = new ObjectMapper();
		String json = "{\"start_time\":\"2023-02-14 13:25:00\",\"end_time\":\"2023-03-14 13:25:00\",\"post_name\":\"dddd\",\"contents\":\"dddd\",\"views\":0,\"durations\":\"2\",\"postCategoryDto\":{\"id\":null,\"spring\":0,\"react\":1,\"python\":1,\"java\":0,\"javascript\":1,\"springboot\":1,\"post_id\":null}}";
		PostDto postDto = objectMapper.readValue(json, PostDto.class);
		dtoService.savePost(postDto,1L);
	}
	@Test
	@DisplayName("게시글 정보 업데이트")
	@Transactional
	void updatePostTest() throws JsonProcessingException {
		ObjectMapper objectMapper = new ObjectMapper();
		String json = "{\"status\":1,\"start_time\":\"2023-02-14 13:25:00\",\"end_time\":\"2023-03-14 13:25:00\",\"post_name\":\"update\",\"contents\":\"dddd\",\"views\":0,\"durations\":\"2\",\"postCategoryDto\":{\"id\":null,\"spring\":0,\"react\":1,\"python\":1,\"java\":0,\"javascript\":1,\"springboot\":1,\"post_id\":null}}";
		PostDto postDto = objectMapper.readValue(json, PostDto.class);
		dtoService.savePost(postDto,1L);
	}

	@Test
	@DisplayName("틀린 정보로 게시글 정보 업데이트")
	@Transactional
	void updateWrongPostTest() throws JsonProcessingException {
		ObjectMapper objectMapper = new ObjectMapper();
		String json = "{\"start_time\":\"2023\",\"end_time\":\"2023-03-14 13:25:00\",\"post_name\":\"update\",\"contents\":\"dddd\",\"views\":0,\"durations\":\"2\",\"postCategoryDto\":{\"id\":null,\"spring\":0,\"react\":1,\"python\":1,\"java\":0,\"javascript\":1,\"springboot\":1,\"post_id\":null}}";
		PostDto postDto = objectMapper.readValue(json, PostDto.class);
		dtoService.savePost(postDto,1L);
	}

	@Test
	@DisplayName("유저 포트폴리오 정보 저장 및 업데이트")
	@Transactional
	void saveUserPortInfoTest() throws JsonProcessingException {
		ObjectMapper objectMapper = new ObjectMapper();
		String json = "{\"school\":\"가천대학교\",\"tech\":\"\",\"userCategoryDto\":{\"spring\":1,\"react\":1,\"python\":1,\"java\":1,\"javascript\":1,\"springboot\":1,\"user_id\":1}}";
		UserDto userDto = objectMapper.readValue(json,UserDto.class);
		dtoService.updatePort(1L,userDto,"Test");
	}

	@Test
	@DisplayName("틀린 정보로 유저 포트폴리오 정보 저장 및 업데이트")
	@Transactional
	void saveWrongUserPortInfoTest() throws JsonProcessingException {
		ObjectMapper objectMapper = new ObjectMapper();
		String json = "{\"userCategoryDto\":{\"spring\":1,\"react\":1,\"python\":1,\"java\":1,\"javascript\":1,\"springboot\":1,\"user_id\":1}}";
		UserDto userDto = objectMapper.readValue(json,UserDto.class);
		dtoService.updatePort(1L,userDto,"Test");
	}


}
