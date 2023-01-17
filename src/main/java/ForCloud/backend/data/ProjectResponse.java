package ForCloud.backend.data;

import ForCloud.backend.entity.Applicant;
import ForCloud.backend.entity.Post;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.CascadeType;
import javax.persistence.OneToMany;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProjectResponse {

    private String name;
    private String title;
    private String period;
    private String duration;
    private String contents;

}
