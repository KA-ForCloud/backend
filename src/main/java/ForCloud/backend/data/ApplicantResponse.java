package ForCloud.backend.data;

import ForCloud.backend.entity.Applicant;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ApplicantResponse {
    private String name;
    private String requested;

    public ApplicantResponse(Applicant applicant){
        this.name = applicant.getMember().getName();
        this.requested = applicant.getRequest();
    }
}
