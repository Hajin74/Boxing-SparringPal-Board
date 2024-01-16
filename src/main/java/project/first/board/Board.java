package project.first.board;

import jakarta.persistence.*;
import lombok.*;
import project.first.post.Post;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@RequiredArgsConstructor
public class Board {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "board_id")
    private Long id;

    private String title;

    @OneToMany(mappedBy = "board", cascade = CascadeType.ALL, fetch = FetchType.LAZY) // 양방향 연관관계 매핑
    private List<Post> posts = new ArrayList<>();


    /* 연관관계 메소드 */
    public void addPost(Post post) {
        posts.add(post);
        post.setBoard(this);
    }

    /* 메소드 */
    public void updateBoard(String title) {
        this.title = title;
    }

}
