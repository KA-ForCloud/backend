package ForCloud.backend.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter @Setter
public class Experience {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="experience_id")
    private Long id;

    @ManyToOne
    @JoinColumn(name="portfolio_id")
    private Portfolio portfolio;

    private String title;
    private String contents;
}
