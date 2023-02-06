package ForCloud.backend.data;

import lombok.Data;

@Data
public class PostParticipantResponse {
    private Long memberId;
    private Long postId;
    private Long roomId;

    public PostParticipantResponse(Long memberId, Long postId, Long roomId) {
        this.memberId=memberId;
        this.postId=postId;
        this.roomId=roomId;
    }
}
