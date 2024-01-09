package project.first.comment;

import jakarta.persistence.*;
import project.first.post.Post;
import project.first.user.User;

import java.time.LocalDateTime;

@Entity
public class Comment {

    @Id
    @GeneratedValue
    @Column(name = "comment_id")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "post_id")
    private Post post;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    private LocalDateTime createdAt;

}
