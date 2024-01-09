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

    @OneToMany(mappedBy = "board")
    private List<Post> posts = new ArrayList<>();

}
