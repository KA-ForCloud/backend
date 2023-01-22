package ForCloud.backend.data;

import ForCloud.backend.entity.Participant;
import ForCloud.backend.type.ProjectType;
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
    private ProjectType projectType;
    public RequestParticipant(Participant participant){
        this.postId = participant.getPost().getId();
        this.name = participant.getMember().getName();
        this.category = participant.getCategory();
        this.projectType = participant.getProjectType();
    }
}
