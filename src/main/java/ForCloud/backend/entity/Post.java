package ForCloud.backend.entity;

import ForCloud.backend.dto.PostDto;
import ForCloud.backend.type.PostType;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Entity
@Getter @Setter
@Table(name="post")
public class Post {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="post_id")
    private Long id;

    @ManyToOne
    @JsonBackReference
    @JoinColumn(name="user_id")
    private User user;

    private String post_name;
    private String start_time;
    private String end_time;
    private Long duration;

    @JsonManagedReference
    @OneToMany(mappedBy = "post",cascade = CascadeType.ALL)
    private List<PostCategory> post_category;

    private String contents;

    private Integer status;

    @Enumerated(EnumType.STRING)
    private PostType postType;


//    @JsonIgnore
//    @Column
//    private Long user_id;

    @JsonManagedReference
    @OneToMany(mappedBy="post",cascade = CascadeType.ALL)
    private List<Participant> participants;

    @JsonManagedReference
    @OneToMany(mappedBy="post",cascade = CascadeType.ALL)
    private List<Applicant> applicants;

    private Long views;

    public void setByDto(PostDto postDto){
        this.post_name = postDto.getPost_name();
        this.start_time = postDto.getStart_time();
        this.end_time = postDto.getEnd_time();
        this.contents = postDto.getContents();
        this.status = postDto.getStatus();
        this.views = postDto.getViews();
        this.duration = postDto.getDurations();
    }
}
