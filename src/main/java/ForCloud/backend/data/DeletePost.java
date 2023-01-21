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
    private Long memberId;

    public DeletePost(Post post){
        this.memberId = post.getMember().getId();
        this.postId = post.getId();
    }
}
