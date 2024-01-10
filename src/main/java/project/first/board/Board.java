package project.first.board;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import project.first.post.Post;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@RequiredArgsConstructor
public class Board {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "board_id")
    private Long id;

    private String title;

    @OneToMany(mappedBy = "board") // 양방향 연관관계 매핑
    private List<Post> posts = new ArrayList<>();

    // 연관관계 메소드
    public void addPost(Post post) {
        posts.add(post);
        post.setBoard(this);
    }

}
