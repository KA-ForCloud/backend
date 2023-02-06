package ForCloud.backend.data;

import ForCloud.backend.entity.Participant;
import ForCloud.backend.type.ParticipantType;
import ForCloud.backend.type.ProjectType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RequestParticipant {
    private Long postId;
    private Long userId;


    public RequestParticipant(Participant participant){
        this.postId = participant.getPost().getId();
        this.userId = participant.getUser().getId();
    }
}
