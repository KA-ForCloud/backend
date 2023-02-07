package ForCloud.backend.data;

import lombok.Data;

@Data
public class PostParticipantResponse {
    private Long memberId;
    private Long postId;
    private Long roomId;
    private String name;

    public PostParticipantResponse(Long memberId, Long postId, Long roomId,String name) {
        this.memberId=memberId;
        this.postId=postId;
        this.roomId=roomId;
        this.name=name;
    }
}
