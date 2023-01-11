package ForCloud.backend.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Entity
@Getter @Setter
@Table(name="member")
public class Member {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="member_id")
    private Long id;

    private String nickname;

//    @OneToMany(mappedBy = "post",cascade = CascadeType.ALL)
//    private List<Post> postList;
//
//    @OneToMany(mappedBy="chatting",cascade=CascadeType.ALL)
//    private List<Chatting> chattingList;

    private Float temperature;

    private String email;

}
