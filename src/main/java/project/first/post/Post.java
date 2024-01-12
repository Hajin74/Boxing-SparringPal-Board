package project.first.post;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import project.first.board.Board;
import project.first.user.User;

import java.time.LocalDateTime;

@Entity
@Getter
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

    private LocalDateTime createdAt;

    // 연관관계 메소드
    public void setBoard(Board board) {
        this.board = board;
        board.getPosts().add(this);
    }

    public void setUser(User user) {
        this.user = user;
        user.getPosts().add(this);
    }

}
