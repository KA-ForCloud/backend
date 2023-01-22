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

    @ManyToOne
    @JsonBackReference
    @JoinColumn(name="post_id")
    private Post post;

    private String type;

    private Long react;
    private Long javascript;
    private Long spring;
    private Long springboot;
    private Long python;
    private Long java;
}
