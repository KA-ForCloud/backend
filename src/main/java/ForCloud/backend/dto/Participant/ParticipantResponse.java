package ForCloud.backend.dto.Participant;

import ForCloud.backend.entity.Participant;
import ForCloud.backend.type.ParticipantType;
import lombok.Data;

@Data
public class ParticipantResponse {
    private String nickname;
    private Long memberId;
    private ParticipantType type;
    private Long last;

    public ParticipantResponse(Participant p){
        this.nickname=p.getMember().getNickname();
        this.memberId=p.getMember().getId();
        this.type=p.getType();
        this.last=p.getLast();
    }
}
