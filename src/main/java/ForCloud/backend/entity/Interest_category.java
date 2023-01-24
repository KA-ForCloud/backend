package ForCloud.backend.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter @Setter
@Table(name="interest_category")
public class Interest_category {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="interest_category_id")
    private Long id;

    @ManyToOne
    @JoinColumn(name="user_Id")
    private User user;

    private Long react;
    private Long javascript;
    private Long spring;
    private Long springboot;
    private Long python;
    private Long java;
}
