package ForCloud.backend.data;

import ForCloud.backend.entity.Applicant;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RequestApplicant {
    private Long postId;
    private Long memberId;
    private String request;

    public RequestApplicant(Applicant applicant){
        this.memberId = applicant.getMember().getId();
        this.postId = applicant.getPost().getId();
        this.request = applicant.getRequest();
    }
}
