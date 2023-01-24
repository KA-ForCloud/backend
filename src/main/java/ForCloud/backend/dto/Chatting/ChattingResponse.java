package ForCloud.backend.dto.Chatting;

import ForCloud.backend.dto.Participant.ParticipantResponse;
import ForCloud.backend.entity.Chatting;
import ForCloud.backend.entity.Member;
import ForCloud.backend.entity.Participant;
import lombok.Data;

import java.util.List;

@Data
public class ChattingResponse {
    private Long chattingId;
    private String title;
    private List<ParticipantResponse> participantList;
    private Long last;
    public ChattingResponse(Chatting c, List<ParticipantResponse> participantList,Long last){
        this.chattingId=c.getId();
        this.title=c.getTitle();
        this.participantList=participantList;
        this.last=last;
    }
}
