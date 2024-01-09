package project.first.board;

import jakarta.persistence.*;
import project.first.post.Post;

import java.util.ArrayList;
import java.util.List;

@Entity
public class Board {

    @Id
    @GeneratedValue
    @Column(name = "board_id")
    private Long id;

    private String title;

    @OneToMany(mappedBy = "board") // 양방향 연관관계 매핑
    private List<Post> posts = new ArrayList<>();

}
