package ForCloud.backend.entity;

import ForCloud.backend.type.ProjectType;
import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
@Table(name="participant")
public class Participant {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="participant_id")
    private Long id;

    @ManyToOne
    @JsonBackReference
    @JoinColumn(name="user_Id")
    private User user;

    @ManyToOne
    @JsonBackReference
    @JoinColumn(name="post_id")
    private Post post;

    private String category;

    private ProjectType projectType;
}
