package ForCloud.backend.dto;


import ForCloud.backend.entity.Post;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
@Getter
@NoArgsConstructor
@ToString
public class PostDto {
    private Integer status;

    private String start_time;
    private String end_time;
    private String post_name;
    private String contents;
    private Integer views;

    private PostCategoryDto postCategoryDto;

    public PostDto(Post post){
        this.status = post.getStatus();
        this.start_time = post.getStart_time();
        this.end_time = post.getEnd_time();
        this.post_name = post.getPost_name();
        this.contents = post.getContents();
        this.views = post.getViews();
    }

}
