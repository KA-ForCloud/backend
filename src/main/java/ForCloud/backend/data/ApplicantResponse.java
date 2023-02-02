package ForCloud.backend.data;

import ForCloud.backend.entity.Applicant;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ApplicantResponse {
    private Long id;
    private String name;

    private String requested;

    public ApplicantResponse(Applicant applicant){
        this.id = applicant.getId();
        this.name = applicant.getUser().getUser_name();
        this.requested = applicant.getRequest();
    }
}
