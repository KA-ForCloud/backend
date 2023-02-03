package ForCloud.backend.data;

import ForCloud.backend.entity.Applicant;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DeleteApplicant {
    private Long postId;
    private Long userId;

    public DeleteApplicant(Applicant applicant){
        this.postId = applicant.getPost().getId();
        this.userId = applicant.getUser().getId();
    }
}
