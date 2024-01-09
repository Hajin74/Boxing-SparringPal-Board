package project.first.comment;

import jakarta.persistence.*;
import project.first.post.Post;

@Entity
public class Comment {

    @Id
    @GeneratedValue
    @Column(name = "comment_id")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "post_id")
    private Post post;

}
