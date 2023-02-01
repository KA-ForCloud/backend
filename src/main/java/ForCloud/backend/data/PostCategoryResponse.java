package ForCloud.backend.data;

import ForCloud.backend.entity.PostCategory;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PostCategoryResponse {

    private Long react;
    private Long javascript;
    private Long spring;
    private Long springboot;
    private Long python;
    private Long java;

    public PostCategoryResponse(PostCategory postCategory){
        this.react = postCategory.getReact();
        this.javascript = postCategory.getJavascript();
        this.java = postCategory.getJava();
        this.python = postCategory.getPython();
        this.spring = postCategory.getSpring();
        this.springboot = postCategory.getSpringboot();
    }
}
