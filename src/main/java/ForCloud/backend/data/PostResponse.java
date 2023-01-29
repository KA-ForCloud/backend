package ForCloud.backend.data;

import ForCloud.backend.entity.Post;
import ForCloud.backend.entity.PostCategory;
import ForCloud.backend.type.PostType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PostResponse {

    private Long id;
    private String name;
    private String post_name;
    private String start_time;
    private String end_time;
    private String duration;
    private String contents;
    private List<PostCategory> post_category;
    private PostType postType;

    private Integer views;
    public PostResponse(Post post){
        this.id = post.getUser().getId();
        this.name = post.getUser().getUser_name();
        this.post_name = post.getPost_name();
        this.start_time =post.getStart_time();
        this.end_time = post.getEnd_time();
        this.duration = post.getDuration();
        this.contents = post.getContents();
        this.post_category = post.getPost_category();
        this.postType = post.getPostType();
        this.views = post.getViews();
    }
}
