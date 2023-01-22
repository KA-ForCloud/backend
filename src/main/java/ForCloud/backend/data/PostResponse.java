package ForCloud.backend.data;

import ForCloud.backend.entity.Post;
import ForCloud.backend.entity.Post_category;
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
    private String title;
    private String date;
    private String period;
    private String duration;
    private String contents;
    private List<Post_category> post_category;
    private PostType postType;

    private Long view;
    public PostResponse(Post post){
        this.id = post.getId();
        this.name = post.getMember().getName();
        this.title = post.getTitle();
        this.date =post.getDate();
        this.period = post.getPeriod();
        this.duration = post.getDuration();
        this.contents = post.getContents();
        this.post_category = post.getPost_category();
        this.postType = post.getStatus();
        this.view = post.getView();
    }
}
