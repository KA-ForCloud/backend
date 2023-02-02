package ForCloud.backend.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Getter
@NoArgsConstructor
@ToString
public class PostCategoryDto {

    private Long id;

    private Long spring;

    private Long react;

    private Long python;

    private Long java;

    private Long javascript;

    private Long springboot;

    private Long post_id;

    public PostCategoryDto(Long id, Long spring, Long react, Long python, Long java, Long javascript, Long springboot, Long post_id){
        this.id = id;
        this.spring = spring;
        this.react = react;
        this.python = python;
        this.java = java;
        this.javascript = javascript;
        this.springboot = springboot;
        this.post_id = post_id;
    }
}
