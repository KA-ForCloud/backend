package ForCloud.backend.data;

import ForCloud.backend.entity.Post;
import ForCloud.backend.entity.Post_category;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PostResponse {

    private String name;
    private String title;
    private String date;
    private String period;
    private String duration;
    private String contents;
    private Post_category post_category;

    public PostResponse(Post post){
        this.name = post.getMember().getName();
        this.title = post.getTitle();
        this.date =post.getDate();
        this.period = post.getPeriod();
        this.duration = post.getDuration();
        this.contents = post.getContents();
        this.post_category = post.getPost_category();
    }
}
