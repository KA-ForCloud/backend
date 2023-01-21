package ForCloud.backend.entity;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Entity
@Getter @Setter
@NoArgsConstructor @AllArgsConstructor
@Table(name="member")
public class Member {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="member_id")
    private Long id;

    private String email;
    private String name;

    private double temperature;

    @JsonManagedReference
    @OneToMany(mappedBy="member",cascade = CascadeType.ALL)
    private List<Post> posts;

    @JsonManagedReference
    @OneToMany(mappedBy="member",cascade = CascadeType.ALL)
    private List<Participant> participants;

    @JsonManagedReference
    @OneToMany(mappedBy="member",cascade = CascadeType.ALL)
    private List<Applicant> applicants;
}