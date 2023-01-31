package ForCloud.backend.data;

import ForCloud.backend.entity.Post;
import ForCloud.backend.entity.PostCategory;
import ForCloud.backend.type.PostType;
import ForCloud.backend.type.ProjectType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GetProjectListResponse {

    private Long id;
    private String name;
    private String post_name;
    private String start_time;
    private String end_time;
    private String duration;
    private String contents;
    private List<PostCategory> post_category;
    private PostType postType;
    private Long views;
    private ProjectType projectType;

}
