package ForCloud.backend.entity;

import ForCloud.backend.type.PostType;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Entity
@Getter @Setter
@Table(name="post")
public class Post {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="post_id")
    private Long id;

    @ManyToOne
    @JsonBackReference
    @JoinColumn(name="member_id")
    private Member member; // 설문 제작자

    private String title;
    private String period;
    private String duration;

    private String contents;

    @Enumerated(EnumType.STRING)
    private PostType status;
}
