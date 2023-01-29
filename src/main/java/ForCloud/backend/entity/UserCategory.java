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
    private Integer spring;

    @Column(name = "react")
    private Integer react;

    @Column(name = "python")
    private Integer python;

    @Column(name = "java")
    private Integer java;

    @Column(name = "javascript")
    private Integer javascript;

    @Column(name = "springboot")
    private Integer springboot;

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
