package project.first.user;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import project.first.comment.Comment;
import project.first.post.Post;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private Long id;

    @Column(unique = true)
    private String name;

    @Enumerated(EnumType.STRING)
    private UserStance stance;

    @OneToMany(mappedBy = "user") // 양방향 연관관계 매핑
    private List<Post> posts = new ArrayList<>();

    @OneToMany(mappedBy = "user") // 양방향 연관관계 매핑
    private List<Comment> comments = new ArrayList<>();

    // 연관관계 메소드
    public void addPost(Post post) {
        posts.add(post);
        post.setUser(this);
    }

    public void addComment(Comment comment) {
        comments.add(comment);
        comment.setUser(this);
    }

}
