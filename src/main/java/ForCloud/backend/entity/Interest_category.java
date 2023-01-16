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
    @JoinColumn(name="member_id")
    private Member member;

    private Integer react;
    private Integer vue;
    private Integer spring;
    private Integer spring_boot;
    private Integer ML;
    private Integer swift;
    private Integer Kotlin;
}
