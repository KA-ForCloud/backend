package ForCloud.backend.entity;

import ForCloud.backend.type.ParticipantType;
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
    @JoinColumn(name="user_id")
    private User user;

    @ManyToOne
    @JoinColumn(name="chatting_id")
    private Chatting chatting;
    @ManyToOne
    @JsonBackReference
    @JoinColumn(name="post_id")
    private Post post;

    @Enumerated(EnumType.STRING)
    private ParticipantType type;

    private Long last;
    private String category;

    private ProjectType projectType;
}
