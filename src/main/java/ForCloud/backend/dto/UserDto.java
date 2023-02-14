package ForCloud.backend.dto;


import ForCloud.backend.entity.User;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;



@Getter
@NoArgsConstructor
@ToString
public class UserDto {

//    private String user_name;
//
//    private String user_email;
//
//    private String user_age;
//
//    private String user_gender;
//
//    private String user_token;
//
    private String school;
//
//    private String user_image;
    private String tech;
    private String port_name;
//    private String port;

    private UserCategoryDto userCategoryDto;

    public UserDto(User user){
//        this.user_name = user.getUser_name();
//        this.user_age = user.getUser_age();
//        this.user_email = user.getUser_email();
//        this.user_gender = user.getUser_gender();
//        this.user_token=user.getUser_token();
        this.school = user.getSchool();
        this.tech = user.getTech();
        this.port_name = user.getPort();
//        this.port = user.getPort();
//        this.user_image = user.getUser_image();
    }
    public void setPort(String port){
        this.port_name = port;
    }

}
