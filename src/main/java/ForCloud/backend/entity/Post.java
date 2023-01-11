package ForCloud.backend.entity;

import ForCloud.backend.type.PostType;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter @Setter
@Table(name="post")
public class Post {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="post_id")
    private Long id;

    private String title;

    private String contents;

    private Integer thumbnail;

    @Enumerated(EnumType.STRING)
    private PostType status;

    @ManyToOne
    @JoinColumn(name="member_id")
    private Member member;


}
