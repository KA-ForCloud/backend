package ForCloud.backend.service;

import ForCloud.backend.entity.User;
import ForCloud.backend.repository.UserRepository;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpStatusCodeException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;



@Service
public class KakaoService {

    @Autowired
    UserRepository userRepository;

    public User getInfoByKakaoToken(String token){

        String myToken = "Bearer " + token;

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
        headers.set("Authorization", myToken);

        UriComponentsBuilder builder =
                UriComponentsBuilder.fromHttpUrl("https://kapi.kakao.com/v2/user/me");

        HttpEntity<?> entity = new HttpEntity<>(headers);
        HttpEntity<String> response = null;
        RestTemplate restTemplate = new RestTemplate();

        try {
            response = restTemplate.exchange(
                    builder.toUriString(),
                    HttpMethod.GET,
                    entity,
                    String.class);
        } catch (HttpStatusCodeException e) {
            System.out.println("error :" + e);
        }

        System.out.println(response);

        JsonParser jsonParser = new JsonParser();
        JsonElement jsonElement = jsonParser.parse(response.getBody());
        JsonObject jsonObject = jsonElement.getAsJsonObject();
        JsonElement kakao_account = jsonObject.get("kakao_account");

        jsonElement = jsonParser.parse(String.valueOf(kakao_account));
        jsonObject = jsonElement.getAsJsonObject();

        JsonElement nameElement = jsonParser.parse(String.valueOf(jsonObject.get("profile")));
        JsonObject nameObject = nameElement.getAsJsonObject();

        String name = "",email = "",age = "",gender = "", profile = " ";

        name = nameObject.get("nickname").getAsString();
        System.out.println(nameObject);
        profile = nameObject.get("profile_image_url").getAsString();
        System.out.println(profile);
        if (jsonObject.get("is_email_valid").getAsString() == "true"){
            email = jsonObject.get("email").getAsString();
        }
        if (jsonObject.get("has_age_range").getAsString() == "true"){
            age = jsonObject.get("age_range").getAsString();
        }
        if (jsonObject.get("has_gender").getAsString() == "true"){
            gender = jsonObject.get("gender").getAsString();
        }

        User user;
        if (userRepository.findByUser_email(email).isPresent()){
            user = userRepository.findByUser_email(email).get();
            user.setUser_token(token);
            userRepository.save(user);
        }
        else{
            user = new User(name, email,gender, age, token, null, null, profile,null,null);
            userRepository.save(user);
        }
        return user;
    }


}
