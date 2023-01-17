package ForCloud.backend.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter @Setter
@Table(name="portfolio")
public class Portfolio {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="portfolio_id")
    private Long id;

    @OneToOne
    @JoinColumn(name="member_id")
    private Member member;

    private String title;
    private String contents;

}
