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
@Table(name="user")
public class User {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="user_id")
    private Long id;

    private String email;
    private String name;

    private double temperature;

    @JsonManagedReference
    @OneToMany(mappedBy="user",cascade = CascadeType.ALL)
    private List<Post> posts;

    @JsonManagedReference
    @OneToMany(mappedBy="user",cascade = CascadeType.ALL)
    private List<Participant> participants;

    @JsonManagedReference
    @OneToMany(mappedBy="user",cascade = CascadeType.ALL)
    private List<Applicant> applicants;
}