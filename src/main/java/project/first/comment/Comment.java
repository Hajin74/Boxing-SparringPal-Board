package project.first.comment;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import project.first.post.Post;
import project.first.user.User;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
public class Comment {

    @Id
    @GeneratedValue
    @Column(name = "comment_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "post_id")
    private Post post;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    private LocalDateTime createdAt;

    // 연관관계 메소드
    public void setUser(User user) {
        this.user = user;
        user.getComments().add(this);
    }

}
