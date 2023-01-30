package ForCloud.backend.entity;

import ForCloud.backend.dto.UserCategoryDto;
import com.fasterxml.jackson.annotation.JsonIgnore;
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
public class UserCategory {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "usercategory_id", nullable = false)
    private Long category_id;

    @Column(name = "spring")
    private Long spring;

    @Column(name = "react")
    private Long react;

    @Column(name = "python")
    private Long python;

    @Column(name = "java")
    private Long java;

    @Column(name = "javascript")
    private Long javascript;

    @Column(name = "springboot")
    private Long springboot;

    @JsonIgnore
    @JoinColumn(name = "user_id")
    private Long user_id;

    public void setByDto(UserCategoryDto userCategoryDto){
        this.spring = userCategoryDto.getSpring();
        this.springboot = userCategoryDto.getSpringboot();
        this.react = userCategoryDto.getReact();
        this.python = userCategoryDto.getPython();
        this.javascript = userCategoryDto.getJavascript();
        this.java = userCategoryDto.getJava();
        this.user_id = userCategoryDto.getUser_id();
    }
}
