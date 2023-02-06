package ForCloud.backend.data;

import lombok.Data;

@Data
public class PostParticipantResponse {
    private Long memberId;
    private Long postId;
    private String name;
    private Long roomId;

    public PostParticipantResponse(Long memberId, Long postId, String name, Long roomId) {
        this.memberId=memberId;
        this.postId=postId;
        this.name=name;
        this.roomId=roomId;
    }
}
