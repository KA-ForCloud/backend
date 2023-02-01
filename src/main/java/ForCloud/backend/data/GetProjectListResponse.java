package ForCloud.backend.data;

import ForCloud.backend.type.ProjectType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GetProjectListResponse {

    private PostResponse postResponse;
    private ProjectType projectType;

}
