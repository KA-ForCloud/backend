package ForCloud.backend.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

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
}
