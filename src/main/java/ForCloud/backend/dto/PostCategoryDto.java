package ForCloud.backend.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Getter
@NoArgsConstructor
@ToString
public class PostCategoryDto {

    private Integer spring;

    private Integer react;

    private Integer python;

    private Integer java;

    private Integer javascript;

    private Integer springboot;

    private Long post_id;
}
