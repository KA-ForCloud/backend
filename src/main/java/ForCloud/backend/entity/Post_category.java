package ForCloud.backend.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter @Setter
public class Post_category {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="post_category_id")
    private Long id;

    @ManyToOne
    @JoinColumn(name="post_id")
    private Post post;

    private String type;

    private Integer react;
    private Integer vue;
    private Integer spring;
    private Integer spring_boot;
    private Integer ML;
    private Integer swift;
    private Integer kotlin;
}
