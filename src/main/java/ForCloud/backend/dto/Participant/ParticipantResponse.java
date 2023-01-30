package ForCloud.backend.dto.Participant;

import ForCloud.backend.entity.Participant;
import ForCloud.backend.type.ParticipantType;
import lombok.Data;

@Data
public class ParticipantResponse {
    private String nickname;
    private Long userId;
    private ParticipantType type;
    private Long last;

    public ParticipantResponse(Participant p){
        this.nickname=p.getUser().getUser_name();
        this.userId=p.getUser().getId();
        this.type=p.getType();
        this.last=p.getLast();
    }
}
