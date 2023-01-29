package ForCloud.backend.entity;


import ForCloud.backend.dto.UserDto;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;

@Entity
@Getter
@NoArgsConstructor
@ToString
@Setter
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "user_id", nullable = false)
    private Long id;

    @Column(name = "user_name")
    private String user_name;

    @Column(name = "user_email")
    private String user_email;

    @Column(name = "gender")
    private String user_gender;

    @Column(name = "age")
    private String user_age;

    @Column(name = "token")
    private String user_token;

    @Column(name = "school")
    private String school;

    @Column(name = "tech")
    private String tech;

    @Column(name = "portfolio_name")
    private String port;

    @Column(name = "portsave_name")
    private String portsave_name;

    @Column(name = "user_image")
    private String user_image;

    private Float temperature;

    public User(String user_name, String user_email, String gender, String age, String token, String school, String tech, String image, String port_name, String portsave_name){
        this.user_name = user_name;
        this.user_email = user_email;
        this.user_age = age;
        this.user_gender = gender;
        this.user_token = token;
        this.school = school;
        this.tech = tech;
        this.user_image = image;
        this.port = port_name;
        this.portsave_name = portsave_name;
    }

    public void setByDto(UserDto userDto){
//        this.user_name = userDto.getUser_name();
//        this.user_email = userDto.getUser_email();
//        this.user_age = userDto.getUser_age();
//        this.user_gender = userDto.getUser_gender();
//        this.user_token = userDto.getUser_token();
    }

}

