package project.first.post;

import jakarta.persistence.*;
import project.first.board.Board;

@Entity
public class Post {

    @Id
    @GeneratedValue
    @Column(name = "post_id")
    private Long id;

    private String title;

    private String content;

    private PostStatus status;

    @ManyToOne
    @JoinColumn(name = "board_id")
    private Board board;

}
