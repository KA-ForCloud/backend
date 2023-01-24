package ForCloud.backend.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter @Setter
@Table(name="applicant")
public class Applicant {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="applicant_id")
    private Long id;

    @ManyToOne
    @JsonBackReference
    @JoinColumn(name="user_Id")
    private User user;

    @ManyToOne
    @JsonBackReference
    @JoinColumn(name="post_id")
    private Post post;

    //신청한 분야
    private String request;
}
