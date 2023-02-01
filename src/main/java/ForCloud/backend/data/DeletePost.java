package ForCloud.backend.data;


import ForCloud.backend.entity.Post;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DeletePost {

    private Long postId;
    private Long userId;

    public DeletePost(Post post){
        this.userId = post.getUser().getId();
        this.postId = post.getId();
    }
}
