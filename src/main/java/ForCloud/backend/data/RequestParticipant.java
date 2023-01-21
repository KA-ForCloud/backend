package ForCloud.backend.data;

import ForCloud.backend.entity.Participant;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RequestParticipant {
    private Long postId;
    private String name;
    private String category;
    public RequestParticipant(Participant participant){
        this.postId = participant.getPost().getId();
        this.name = participant.getMember().getName();
        this.category = participant.getCategory();
    }
}
