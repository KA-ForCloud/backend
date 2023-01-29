package ForCloud.backend.dto;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Getter
@ToString
@NoArgsConstructor
public class UserCategoryDto {
    private Integer spring;

    private Integer react;

    private Integer python;

    private Integer java;

    private Integer javascript;

    private Integer springboot;

    private Long user_id;

}
