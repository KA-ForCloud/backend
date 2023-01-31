package ForCloud.backend.entity;

import ForCloud.backend.type.ProjectType;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Entity
@Getter @Setter
@Table(name="chatting")
public class Chatting {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="chatting_id")
    private Long id;

    private String title;

    @Column(name="post_id")
    private Long postId;

    private String filePath;

    @JsonManagedReference
    @OneToMany(mappedBy="chatting",cascade = CascadeType.ALL)
    private List<Participant> participantList;

    private ProjectType projectType;
}
