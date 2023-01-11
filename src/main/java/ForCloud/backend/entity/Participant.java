package ForCloud.backend.entity;

import ForCloud.backend.type.ParticipantType;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter @Setter
@Table(name = "participant")
public class Participant {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name="member_id")
    private Member member;

    @ManyToOne
    @JoinColumn(name="chatting_id")
    private Chatting chatting;

    @Enumerated(EnumType.STRING)
    private ParticipantType type;
}
