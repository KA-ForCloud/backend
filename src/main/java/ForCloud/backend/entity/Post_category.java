package ForCloud.backend.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Entity
@Getter @Setter
@Table(name="post_category")
public class Post_category {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="post_category_id")
    private Long id;

    @JsonBackReference
    @OneToOne
    @JoinColumn(name="post_id")
    private Post post;

    private Integer react;
    private Integer javascript;
    private Integer spring;
    private Integer springboot;
    private Integer python;
    private Integer java;
}
