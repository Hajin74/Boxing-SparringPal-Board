package project.first.post;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import project.first.board.Board;
import project.first.comment.Comment;
import project.first.user.User;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EntityListeners(AuditingEntityListener.class)
public class Post {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "post_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "board_id")
    private Board board;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    private String title;

    private String content;

    @Enumerated(EnumType.STRING)
    private PostStatus status;

    @CreatedDate
    @Column(updatable = false)
    private LocalDateTime createdAt;

    @OneToMany(mappedBy = "post", cascade = CascadeType.ALL)
    @OrderBy("createdAt DESC")
    private List<Comment> comments;


    /* 연관관계 편의 메소드 */
    public void setBoard(Board board) {
        this.board = board;
        board.getPosts().add(this);
    }

    public void setUser(User user) {
        this.user = user;
        user.getPosts().add(this);
    }

    /* 메소드 */
    public void updatePost(String title, String content, PostStatus status) {
        this.title = title;
        this.content = content;
        this.status = status;
    }

}
