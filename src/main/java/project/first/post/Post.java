package project.first.post;

import jakarta.persistence.*;
import project.first.board.Board;
import project.first.user.User;

import java.time.LocalDateTime;

@Entity
public class Post {

    @Id
    @GeneratedValue
    @Column(name = "post_id")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "board_id")
    private Board board;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    private String title;

    private String content;

    @Enumerated(EnumType.STRING)
    private PostStatus status;

    private LocalDateTime createdAt;

}
