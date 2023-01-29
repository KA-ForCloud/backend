package ForCloud.backend.entity;

import ForCloud.backend.dto.PostCategoryDto;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter @Setter
@Table(name="post_category")
public class PostCategory {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="post_category_id")
    private Long id;

    @ManyToOne
    @JsonBackReference
    @JoinColumn(name="post_id")
    private Post post;


    private String type;

    private Integer react;
    private Integer javascript;
    private Integer spring;
    private Integer springboot;
    private Integer python;
    private Integer java;

    public void setByDto(PostCategoryDto postCategoryDto, Post post){
        this.spring = postCategoryDto.getSpring();
        this.springboot = postCategoryDto.getSpringboot();
        this.react = postCategoryDto.getReact();
        this.python = postCategoryDto.getPython();
        this.javascript = postCategoryDto.getJavascript();
        this.java = postCategoryDto.getJava();
        this.post = post;
    }
}
