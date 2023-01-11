package ForCloud.backend.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter @Setter
@Table(name="applicant")
public class Applicant {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="applicant_id")
    private Long id;

    @ManyToOne
    @JoinColumn(name="member_id")
    private Member member;

    @ManyToOne
    @JoinColumn(name="post_id")
    private Post post;

}
